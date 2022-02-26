package com.example.perludilindungi

import com.example.perludilindungi.api.CityApi
import com.example.perludilindungi.api.FaskesApi
import com.example.perludilindungi.api.ProvinceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://perludilindungi.herokuapp.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val instance: Api by lazy {
        retrofit.create(Api::class.java)
    }

    val provInstance: ProvinceApi by lazy {
        retrofit.create(ProvinceApi::class.java)
    }

    val cityInstance: CityApi by lazy {
        retrofit.create(CityApi::class.java)
    }

    val daftarFaskesInstance: FaskesApi by lazy {
        retrofit.create(FaskesApi::class.java)
    }

}