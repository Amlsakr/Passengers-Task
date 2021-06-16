package com.example.passengerstask.data.repository

import com.example.passengerstask.data.locale.AirLineRoomDataBase
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.retrofit.AirLineApiHelper
import javax.inject.Inject

class MainRepository  @Inject constructor(private val jobsApiHelper: AirLineApiHelper , private val db: AirLineRoomDataBase) {

    suspend fun getJobsFomInternet() = jobsApiHelper.getPassengers()

    val dao = db.airLineDao()

    suspend fun insertAll(airLineItems: List<AirLineItem>): List<Long> {
        val array = dao.insertAll(airLineItems)
        return array
    }

    suspend fun getAll(): List<AirLineItem> {
        return dao.getAll()
    }

    suspend fun exists(id: String): Boolean {
        return dao.exists(id)
    }

    suspend fun insertItem(airLineItem: AirLineItem) {
        dao.insert(airLineItem)
    }
}