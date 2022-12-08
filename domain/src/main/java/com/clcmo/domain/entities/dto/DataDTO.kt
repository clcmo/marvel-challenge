package com.clcmo.domain.entities.dto

data class DataDTO<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

