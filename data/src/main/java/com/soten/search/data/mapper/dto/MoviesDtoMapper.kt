package com.soten.search.data.mapper.dto

import com.soten.search.data.mapper.Mapper
import com.soten.search.data.network.response.MovieDto
import com.soten.search.data.network.response.MoviesDto
import com.soten.search.domain.MovieDomain
import com.soten.search.domain.MoviesDomain
import javax.inject.Inject

internal class MoviesDtoMapper @Inject constructor() : Mapper<MoviesDto, MoviesDomain> {
    override fun toDomain(data: MoviesDto): MoviesDomain =
        MoviesDomain(data.total, data.start, data.items.map { it.toDomain() })

    override fun toData(domain: MoviesDomain): MoviesDto =
        MoviesDto(domain.total, domain.start, domain.movies.map { it.toData() })
}

internal fun MovieDto.toDomain() =
    MovieDomain(title, link, image, pubDate, userRating)

internal fun MovieDomain.toData() =
    MovieDto(title, link, image, pubDate, userRating)









