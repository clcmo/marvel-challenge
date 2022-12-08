package com.clcmo.domain.entities.dto

data class ResponseDTO<T>(
    val data: DataDTO<T>
)

