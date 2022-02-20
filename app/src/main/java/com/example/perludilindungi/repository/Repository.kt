package com.example.perludilindungi.repository

import com.example.perludilindungi.RetrofitClient
import com.example.perludilindungi.models.ProvinceResponse

class Repository {
    suspend fun getProvince(): ProvinceResponse {
        return RetrofitClient.provInstance.getProvince()
    }
}