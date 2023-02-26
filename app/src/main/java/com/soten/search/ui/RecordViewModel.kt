package com.soten.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.search.domain.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _queries = MutableStateFlow(emptyList<String>())
    val queries = _queries.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.fetchQueries().collect {queries ->
                _queries.update { queries }
            }
        }
    }
}