package com.example.databaseapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kontak(
    val id : Int,
    @SerialName("nama")
    val name :  String,
    @SerialName("email")
    val email : String,
    @SerialName("nohp")
    val telpon : String,
)
