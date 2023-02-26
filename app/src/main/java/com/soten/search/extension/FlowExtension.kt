package com.soten.search.extension

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun <T> MutableStateFlow<List<T>>.addAll(list: List<T>) {
    update {
        val currentList = it.toMutableList()
        currentList.addAll(list)
        currentList.toList()
    }
}