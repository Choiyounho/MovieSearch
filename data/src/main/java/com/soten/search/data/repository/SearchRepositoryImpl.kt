package com.soten.search.data.repository

import com.soten.search.data.db.MovieDao
import com.soten.search.data.mapper.dto.MoviesDtoMapper
import com.soten.search.data.mapper.entity.EntityMapper
import com.soten.search.data.network.MovieSearchApi
import com.soten.search.domain.MoviesDomain
import com.soten.search.domain.SearchRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: MovieSearchApi,
    private val movieDao: MovieDao,
    private val moviesDtoMapper: MoviesDtoMapper,
    private val entityMapper: EntityMapper
): SearchRepository {

    override suspend fun fetchSearchMovies(query: String, start: Int): MoviesDomain {
        return runCatching {
            moviesDtoMapper.toDomain(searchApi.search(query = query, start = start))
        }.getOrDefault(MoviesDomain.EMPTY)
    }

    override suspend fun insertMovie(query: String) {
        movieDao.insertQueryEntity(queryEntity = entityMapper.toData(query))

        val movies = movieDao.findAllByQueryEntity().firstOrNull() ?: return
        if (movies.size > 10) {
            movieDao.findByOldestQueryEntity()?.let {
                movieDao.deleteQueryEntity(it)
            }
        }
    }

    @OptIn(FlowPreview::class)
    override suspend fun fetchQueries(): Flow<List<String>> {
        return movieDao.findAllByQueryEntity().flatMapConcat {
            flowOf(it.map { entityMapper.toDomain(it) })
        }
    }
}