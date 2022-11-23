package com.kamal.sahlsample.data.models.request


@kotlinx.serialization.Serializable
data class LoginRequest(val email:String, val password:String)
