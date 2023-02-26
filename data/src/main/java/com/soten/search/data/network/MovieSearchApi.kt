package com.soten.search.data.network

import com.soten.search.data.BuildConfig
import com.soten.search.data.network.response.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface MovieSearchApi {

    @GET("v1/search/movie.json")
    suspend fun search(
        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.CLIENT_ID,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("query") query: String,
        @Query("display") display: Int = DEFAULT_ITEM_SIZE,
        @Query("start") start: Int = DEFAULT_START_INDEX
    ): MoviesDto

    companion object {
        private const val DEFAULT_ITEM_SIZE = 20
        private const val DEFAULT_START_INDEX = 1
    }
}