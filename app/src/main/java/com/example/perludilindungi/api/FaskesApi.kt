package com.example.perludilindungi.api

import com.example.perludilindungi.models.FaskesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FaskesApi {
    @GET("api/get-faskes-vaksinasi")
    suspend fun getDaftarFaskes(
        @Query("province") province: String,
        @Query("city") city: String,
    ): FaskesResponse
}
