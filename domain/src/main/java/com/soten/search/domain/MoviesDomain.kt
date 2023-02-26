package com.soten.search.domain

data class MoviesDomain(
    val total: Int,
    val start: Int,
    val movies: List<MovieDomain>
) {
    companion object {
        val EMPTY = MoviesDomain(0, -1, emptyList())
    }
}
