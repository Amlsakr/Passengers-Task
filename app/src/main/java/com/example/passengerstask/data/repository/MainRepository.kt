package com.example.passengerstask.data.repository

import com.example.passengerstask.data.locale.AirLineRoomDataBase
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.retrofit.AirLineApiHelper
import javax.inject.Inject

open class MainRepository  @Inject constructor(private val jobsApiHelper: AirLineApiHelper , private val db: AirLineRoomDataBase) {

    suspend fun getJobsFomInternet() = jobsApiHelper.getPassengers()

    val dao = db.airLineDao()
    suspend fun getAll(): List<AirLineItem> {
        return dao.getAll()
    }
    suspend fun insertItem(airLineItem: AirLineItem) {
        dao.insert(airLineItem)
    }
}