package com.example.vitea.models.da

import androidx.annotation.Keep

@Keep
data class Assignment (
    val title: String,
    val lastDate: String,
    val courseName: String,
    val faculty: Faculty
)