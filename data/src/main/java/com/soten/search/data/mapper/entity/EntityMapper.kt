package com.soten.search.data.mapper.entity

import com.soten.search.data.db.QueryEntity
import com.soten.search.data.mapper.Mapper
import javax.inject.Inject

internal class EntityMapper @Inject constructor(): Mapper<QueryEntity, String> {

    override fun toDomain(data: QueryEntity): String {
        return data.query
    }

    override fun toData(domain: String): QueryEntity {
        return QueryEntity(domain, System.currentTimeMillis())
    }
}