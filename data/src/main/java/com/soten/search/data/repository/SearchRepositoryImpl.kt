package com.soten.search.data.repository

import com.soten.search.data.db.MovieDao
import com.soten.search.data.mapper.dto.MovieDtoMapper
import com.soten.search.data.network.MovieSearchApi
import com.soten.search.domain.MovieDomain
import com.soten.search.domain.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: MovieSearchApi,
    private val movieDao: MovieDao,
    private val movieDtoMapper: MovieDtoMapper
): SearchRepository {

    override suspend fun fetchSearchMovies(query: String, start: Int): List<MovieDomain> {
        return runCatching {
            searchApi.search(query = query, start = start).items.map { movieDtoMapper.toDomain(it) }
        }.getOrDefault(emptyList())
    }

    override suspend fun insertMovie(movieDomain: MovieDomain) {
        movieDao.insertMovieEntity(movieEntity = movieDtoMapper::)
    }
}