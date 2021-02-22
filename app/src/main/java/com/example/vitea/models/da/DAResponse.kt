package com.example.vitea.models.da

import androidx.annotation.Keep

@Keep
data class DAResponse (
    val assignments: List<Assignment>
)