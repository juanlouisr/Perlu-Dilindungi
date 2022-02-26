package com.example.perludilindungi.api

import com.example.perludilindungi.models.CheckInResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInApi {
    @POST("check-in")
    suspend fun postCheckIn(@Body requestBody: RequestBody): CheckInResponse
}