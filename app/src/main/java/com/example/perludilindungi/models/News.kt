package com.example.perludilindungi.models

data class News(
    val description: NewsDescription,
    val enclosure: NewsEnclosure,
    val guid: String,
    val link: List<String>,
    val pubDate: String,
    val title: String
)