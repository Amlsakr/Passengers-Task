package com.example.passengerstask.data.retrofit


import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.utilities.Resource
import retrofit2.Response
import retrofit2.http.Query

interface AirLineApiHelper {

    suspend fun getPassengers( ): Response<List<AirLineItem>>
}