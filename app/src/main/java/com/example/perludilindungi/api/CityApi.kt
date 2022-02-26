package com.example.perludilindungi.api

import com.example.perludilindungi.models.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {
    @GET("api/get-city")
    suspend fun getCity(
        @Query("start_id") startId: String
    ): CityResponse
}


