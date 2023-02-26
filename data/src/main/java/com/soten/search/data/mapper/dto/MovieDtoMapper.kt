package com.soten.search.data.mapper.dto

import com.soten.search.data.mapper.Mapper
import com.soten.search.data.network.response.MovieDto
import com.soten.search.domain.MovieDomain
import javax.inject.Inject

internal class MovieDtoMapper @Inject constructor(): Mapper<MovieDto, MovieDomain> {

    override fun toDomain(data: MovieDto) =
        MovieDomain(data.title, data.link, data.image, data.pubDate, data.userRating)

    override fun toData(domain: MovieDomain) =
        MovieDto(domain.title, domain.link, domain.image, domain.pubDate, domain.userRating)
}