package com.example.perludilindungi.models

data class CityResponse (
    val curr_val: String,
    val message: String,
    val results: ArrayList<City>,
)