package com.vid35.controldeventas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vid35.controldeventas.dao.ClienteDao
import com.vid35.controldeventas.entity.Cliente
import com.vid35.controldeventas.event.ClienteEvent
import com.vid35.controldeventas.misc.ClienteSortType
import com.vid35.controldeventas.misc.ClienteState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ClienteViewModel(
    private val dao: ClienteDao
): ViewModel() {
    private val _sortType = MutableStateFlow(ClienteSortType.NOMBRE)
    private val _clientes = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                ClienteSortType.NOMBRE -> dao.getClientesOrderedByNombre()
                ClienteSortType.APELLIDO -> dao.getClientesOrderedByApellido()
                ClienteSortType.ALIAS -> dao.getClientesOrderedByAlias()
                ClienteSortType.TELEFONO -> dao.getClientesOrderedByTelefono()
            }
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        }
    private val _state = MutableStateFlow(ClienteState())
    val state = combine(_state, _sortType, _clientes) { state, sortType, clientes ->
        state.copy(
            clientes = clientes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ClienteState())

    fun onEvent(event: ClienteEvent) {
        when(event) {
            is ClienteEvent.DeleteCliente -> {
                viewModelScope.launch {
                    dao.deleteCliente(event.cliente)
                }
            }
            ClienteEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCliente = false
                ) }
            }
            ClienteEvent.SaveCliente -> {
                val nombreCliente = state.value.nombreCliente
                val apellidoCliente = state.value.apellidoCliente
                val aliasCliente = state.value.aliasCliente
                val telefonoCliente = state.value.telefonoCliente

                if (nombreCliente.isBlank() || apellidoCliente.isBlank()
                    || aliasCliente.isBlank()
                    || telefonoCliente.isBlank()) {
                    return
                }

                val cliente = Cliente(
                    nombreCliente = nombreCliente,
                    apellidoCliente = apellidoCliente,
                    aliasCliente = aliasCliente,
                    telefonoCliente = telefonoCliente
                )
                viewModelScope.launch {
                    dao.upsertCliente(cliente)
                }
                _state.update { it.copy(
                    isAddingCliente = false,
                    nombreCliente = "",
                    apellidoCliente = "",
                    aliasCliente = "",
                    telefonoCliente = ""
                )}

            }
            is ClienteEvent.SetAliasCliente -> {
                _state.update { it.copy(
                    aliasCliente = event.aliasCliente
                ) }
            }
            is ClienteEvent.SetApellidoCliente -> {
                _state.update { it.copy(
                    apellidoCliente = event.apellidoCliente
                ) }
            }
            is ClienteEvent.SetNombreCliente -> {
                _state.update { it.copy(
                    nombreCliente = event.nombreCliente
                ) }
            }
            is ClienteEvent.SetTelefonoCliente -> {
                _state.update { it.copy(
                    telefonoCliente = event.telefonoCliente
                ) }
            }
            ClienteEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingCliente = true
                ) }
            }
            is ClienteEvent.SortClientes -> {
                _sortType.value = event.sortType
            }
        }
    }
}