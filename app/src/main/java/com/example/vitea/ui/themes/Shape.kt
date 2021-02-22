package com.example.vitea.ui.themes

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(10.dp),
    large = CutCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 0.dp
    )
)
