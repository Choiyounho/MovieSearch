package com.soten.search.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieDto(
    @Json(name = "title")
    val title: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "pubDate")
    val pubDate: String,
    @Json(name = "userRating")
    val userRating: String
)