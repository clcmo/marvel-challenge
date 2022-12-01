package com.clcmo.core.domain.entities

data class PaginatedData<T>(
    val data: List<Char>,
    val totalData: Int
)