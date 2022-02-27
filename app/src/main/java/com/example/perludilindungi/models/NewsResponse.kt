package com.example.perludilindungi.models

data class NewsResponse(
    val count_total: Int,
    val message: String,
    val results: ArrayList<News>,
    val success: Boolean
)