package com.example.perludilindungi.api

import com.example.perludilindungi.models.ProvinceResponse
import retrofit2.http.GET

interface ProvinceApi {
    @GET("api/get-province")
    suspend fun getProvince(): ProvinceResponse
}