package com.soten.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.search.domain.MovieDomain
import com.soten.search.domain.SearchRepository
import com.soten.search.domain.SearchRepository.Companion.DEFAULT_START
import com.soten.search.extension.addAll
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieDomain>>(emptyList())
    val movies = _movies.asStateFlow()

    fun fetchMovies(query: String, start: Int = DEFAULT_START) {
        _movies.update { emptyList() }
        viewModelScope.launch {
            val result = searchRepository.fetchSearchMovies(query, start)
            _movies.addAll(result)
        }
    }
}