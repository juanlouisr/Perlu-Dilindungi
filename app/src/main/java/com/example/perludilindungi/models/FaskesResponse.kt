package com.example.perludilindungi.models

data class FaskesResponse(
    val success: Boolean,
    val message: String,
    val count_total: Int,
    val data: List<Faskes>,
)
