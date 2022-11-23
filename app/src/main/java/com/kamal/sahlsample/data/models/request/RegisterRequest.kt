package com.kamal.sahlsample.data.models.request


@kotlinx.serialization.Serializable
data class RegisterRequest(val name:String, val mobile :String , val email:String, val password:String)
