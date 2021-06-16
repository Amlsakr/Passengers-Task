package com.example.passengerstask.data.repository

import com.example.passengerstask.data.retrofit.AirLineApiHelper
import javax.inject.Inject

class MainRepository  @Inject constructor(private val jobsApiHelper: AirLineApiHelper) {

    suspend fun getJobsFomInternet() = jobsApiHelper.getPassengers()
}