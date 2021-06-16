package com.example.passengerstask.data.retrofit

import com.example.passengerstask.data.model.AirLineItem

import retrofit2.Response
import javax.inject.Inject

class AirLineHelperImplementation @Inject constructor(private val airLineApiService: AirLineApiService) : AirLineApiHelper {
    override suspend fun getPassengers(): Response<List<AirLineItem>>
    = airLineApiService.getPassengers()
}