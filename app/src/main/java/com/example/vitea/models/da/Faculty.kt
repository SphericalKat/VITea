package com.example.vitea.models.da

import androidx.annotation.Keep

@Keep
data class Faculty (
    val id: String,
    val name: String,
    val cabin: String,
    val email: String
)