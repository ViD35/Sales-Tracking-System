package com.vid35.controldeventas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vid35.controldeventas.dao.ClienteDao
import com.vid35.controldeventas.entity.Cliente

@Database(
    entities = [Cliente::class],
    version = 1
)
abstract class ClienteDatabase: RoomDatabase() {
    abstract val dao:ClienteDao
}