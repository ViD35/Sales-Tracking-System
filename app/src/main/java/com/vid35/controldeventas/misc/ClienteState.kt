package com.vid35.controldeventas.misc

import com.vid35.controldeventas.entity.Cliente

data class ClienteState(
    val clientes: List<Cliente> = emptyList(),
    val nombreCliente: String = "",
    val apellidoCliente: String = "",
    val aliasCliente: String = "",
    val telefonoCliente: String = "",
    val isAddingCliente: Boolean = false,
    val sortType: ClienteSortType = ClienteSortType.NOMBRE
    )
