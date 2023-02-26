package com.soten.search.data.repository

import com.soten.search.data.db.MovieDao
import com.soten.search.data.db.QueryEntity
import com.soten.search.data.mapper.dto.MovieDtoMapper
import com.soten.search.data.mapper.entity.EntityMapper
import com.soten.search.data.network.MovieSearchApi
import com.soten.search.domain.MovieDomain
import com.soten.search.domain.SearchRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: MovieSearchApi,
    private val movieDao: MovieDao,
    private val movieDtoMapper: MovieDtoMapper,
    private val entityMapper: EntityMapper
): SearchRepository {

    override suspend fun fetchSearchMovies(query: String, start: Int): List<MovieDomain> {
        return runCatching {
            searchApi.search(query = query, start = start).items.map { movieDtoMapper.toDomain(it) }
        }.getOrDefault(emptyList())
    }

    override suspend fun insertMovie(query: String) {
        movieDao.insertQueryEntity(queryEntity = entityMapper.toData(query))
    }

    @OptIn(FlowPreview::class)
    override suspend fun fetchQueries(): Flow<List<String>> {
        return movieDao.findAllByQueryEntity().flatMapConcat {
            flowOf(it.map { entityMapper.toDomain(it) })
        }
    }
}