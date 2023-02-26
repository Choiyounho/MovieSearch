package com.soten.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.search.domain.MoviesDomain
import com.soten.search.domain.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _movies = MutableStateFlow(MoviesDomain.EMPTY)
    val movies = _movies.asStateFlow()

    private val _query = MutableStateFlow("")
    private val query = _query.asStateFlow()

    fun searchMovies(query: String) {
        setQuery(query)

        _movies.update { MoviesDomain.EMPTY }

        viewModelScope.launch {
            val result = searchRepository.fetchSearchMovies(query)
            _movies.update { result }
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            val total = movies.value.total
            val size = movies.value.movies.size

            val start = if (size >= total) {
                return@launch
            } else size + 1

            val result = searchRepository.fetchSearchMovies(query.value, start)
            addMoviesDomain(result)
        }
    }

    private fun setQuery(query: String) {
        _query.update { query }

        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.insertMovie(query)
        }
    }

    private fun addMoviesDomain(moviesDomain: MoviesDomain) {
        _movies.update {
            val currentList = it.movies.toMutableList()
            currentList.addAll(moviesDomain.movies)
            it.copy(
                moviesDomain.total,
                moviesDomain.start,
                currentList.toList()
            )
        }
    }
}