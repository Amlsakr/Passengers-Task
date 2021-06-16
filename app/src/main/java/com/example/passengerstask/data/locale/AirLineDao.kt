package com.example.passengerstask.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passengerstask.data.model.AirLineItem

@Dao
interface AirLineDao {

    @Query("SELECT * FROM airLineTable")
    suspend fun getAll(): List<AirLineItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(airLineItems: List<AirLineItem>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(airLineItem: AirLineItem)



    @Query("SELECT EXISTS (SELECT 1 FROM airLineTable WHERE id = :id)")
    suspend fun exists(id: String): Boolean
}