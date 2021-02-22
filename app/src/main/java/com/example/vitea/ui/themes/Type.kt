package com.example.vitea.ui.themes

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vitea.R

val CircularStd = FontFamily(
    Font(R.font.circularstd_black, FontWeight.Black),
    Font(R.font.circularstd_medium, FontWeight.Medium),
    Font(R.font.circularstd_bold, FontWeight.Bold),
    Font(R.font.circularstd_book, FontWeight(350))
)

val typography = Typography(
    defaultFontFamily = CircularStd,
    h4 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp,
        color = Color.White
    ),
    h5 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        color = Color.White
    ),
    h6 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        color = Color.White
    ),
    subtitle1 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        color = Color.White
    ),
    subtitle2 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = Color.White
    ),
    body1 = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    body2 = TextStyle(
        fontFamily = CircularStd,
        fontSize = 14.sp,
        color = Color.White
    ),
    button = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = Color.White
    ),
    caption = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White
    ),
    overline = TextStyle(
        fontFamily = CircularStd,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        color = Color.White
    )
)