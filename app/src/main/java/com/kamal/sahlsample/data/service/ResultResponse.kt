package com.kamal.sahlsample.data.service

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse<T>(
    val status: Boolean,
    val data: T?,
    val error: String? = null,
)