package com.soten.search.data.mapper

interface Mapper<DT, D> {

    fun toDomain(data: DT): D
    fun toData(domain: D): DT
}