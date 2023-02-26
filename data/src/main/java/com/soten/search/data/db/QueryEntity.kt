package com.soten.search.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QueryEntity(
    val query: String,
    @PrimaryKey
    val createAt: Long? = null
)
