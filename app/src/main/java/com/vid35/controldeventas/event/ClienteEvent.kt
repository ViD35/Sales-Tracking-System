package com.vid35.controldeventas.event

import com.vid35.controldeventas.ClienteSortType
import com.vid35.controldeventas.entity.Cliente

sealed interface ClienteEvent {
    object SaveCliente: ClienteEvent
    data class SetNombreCliente(val nombreCliente: String): ClienteEvent
    data class SetApellidoCliente(val apellidoCliente: String): ClienteEvent
    data class SetAliasCliente(val aliasCliente: String): ClienteEvent
    data class SetTelefonoCliente(val telefonoCliente: String): ClienteEvent
    data class SetDireccionCliente(val direccionCliente: String): ClienteEvent
    object ShowDialog: ClienteEvent
    object HideDialog: ClienteEvent
    data class SortClientes(val sortType: ClienteSortType): ClienteEvent
    data class DeleteCliente(val cliente: Cliente): ClienteEvent
}