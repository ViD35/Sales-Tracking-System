package com.vid35.controldeventas.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vid35.controldeventas.entity.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Upsert
    suspend fun upsertCliente(cliente: Cliente)

    @Delete
    suspend fun deleteCliente(cliente: Cliente)

    @Query("SELECT * FROM cliente ORDER BY nombre_cliente ASC")
    fun getClientesOrderedByNombre(): Flow<List<Cliente>>

    @Query("SELECT * FROM cliente ORDER BY apellido_cliente ASC")
    fun getClientesOrderedByApellido(): Flow<List<Cliente>>

    @Query("SELECT * FROM cliente ORDER BY alias_cliente ASC")
    fun getClientesOrderedByAlias(): Flow<List<Cliente>>

    @Query("SELECT * FROM cliente ORDER BY telefono_cliente ASC")
    fun getClientesOrderedByTelefono(): Flow<List<Cliente>>

    @Query("SELECT * FROM cliente ORDER BY direccion_cliente ASC")
    fun getClientesOrderedByDireccion(): Flow<List<Cliente>>

}