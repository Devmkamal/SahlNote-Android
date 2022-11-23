package com.kamal.sahlsample.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(val id : String, val text: String, val userId: String, val createdAt : Long)