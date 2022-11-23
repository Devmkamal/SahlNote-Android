package com.kamal.sahlsample.data.models

import kotlinx.serialization.Serializable


@Serializable
data class User(val id : String,
                val name : String,
                val email : String,
                val mobile : Int,
                val token : String)
