package com.soten.search.domain

import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    suspend fun fetchSearchMovies(query: String, start: Int = DEFAULT_START): MoviesDomain

    suspend fun insertMovie(query: String)

    suspend fun fetchQueries(): Flow<List<String>>

    companion object {
        const val DEFAULT_START = 1
    }
}