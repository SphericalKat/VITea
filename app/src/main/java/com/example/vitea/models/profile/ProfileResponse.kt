package com.example.vitea.models.profile

import androidx.annotation.Keep

@Keep
data class ProfileResponse (
    val regNo: String,
    val img: String,
    val gender: String,
    val school: String,
    val studentName: String,
    val description: String,
    val program: String,
    val mobileNo: String,
    val email: String
)
