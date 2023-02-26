package com.soten.search.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QueryEntity(
    @PrimaryKey
    val query: String,
    val createAt: Long
)
