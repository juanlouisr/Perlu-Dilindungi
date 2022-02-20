package com.example.perludilindungi

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("api/get-news")
    fun getNews(): Call<ArrayList<NewsResponse>>
}