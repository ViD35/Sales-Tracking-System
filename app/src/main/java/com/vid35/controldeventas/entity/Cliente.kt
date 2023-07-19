package com.vid35.controldeventas.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente (
    @PrimaryKey val idCliente : Int,
    @ColumnInfo(name = "nombre_cliente") val nombreCliente : String?,
    @ColumnInfo(name = "apellido_cliente") val apellidoCliente : String?,
    @ColumnInfo(name = "alias_cliente") val aliasCliente : String?,
    @ColumnInfo(name = "telefono_cliente") val telefonoCliente : String?,
    @ColumnInfo(name = "direccion_cliente") val direccionCliente : String?
)