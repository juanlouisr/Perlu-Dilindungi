package com.example.perludilindungi.repository

import com.example.perludilindungi.RetrofitClient
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.models.ProvinceResponse

class Repository {
    suspend fun getProvince(): ProvinceResponse {
        return RetrofitClient.provInstance.getProvince()
    }

    suspend fun getCity(startId: String): CityResponse{
        return RetrofitClient.cityInstance.getCity(startId)
    }

    suspend fun getFaskes(province: String, city: String): FaskesResponse{
        return RetrofitClient.daftarFaskesInstance.getDaftarFaskes(province, city)
    }

}