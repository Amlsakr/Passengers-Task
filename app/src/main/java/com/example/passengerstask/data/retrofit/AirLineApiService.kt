package com.example.passengerstask.data.retrofit


import com.example.passengerstask.data.model.AirLineItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirLineApiService {

    @GET("airlines")
    suspend fun getPassengers(): Response<List<AirLineItem>>


}