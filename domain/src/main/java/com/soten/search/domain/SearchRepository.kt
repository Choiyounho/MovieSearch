package com.soten.search.domain

interface SearchRepository {

    suspend fun fetchSearchMovies(query: String, start: Int = DEFAULT_START): List<MovieDomain>

    suspend fun insertMovie(movieDomain: MovieDomain)

    companion object {
        private const val DEFAULT_START = 1
    }
}