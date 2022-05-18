package com.example.apimars.network

import com.squareup.moshi.Json

data class MarsState(
    val id: String,
    @Json(name = "img_src") val img_src: String,
    val price: Int,
    val type: String
)