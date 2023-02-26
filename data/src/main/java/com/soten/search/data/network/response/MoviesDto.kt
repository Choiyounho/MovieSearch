package com.soten.search.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MoviesDto(
    @Json(name = "total")
    val total: Int,
    @Json(name = "start")
    val start: Int,
    @Json(name = "items")
    val items: List<MovieDto>
)