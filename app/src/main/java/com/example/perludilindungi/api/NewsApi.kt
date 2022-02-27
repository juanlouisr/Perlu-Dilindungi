package com.example.perludilindungi.api

import com.example.perludilindungi.models.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface NewsApi {
    @GET("api/get-news")
    suspend fun getNews(): NewsResponse
}