package com.example.vitea.utils

import android.annotation.SuppressLint
import com.example.vitea.models.da.Assignment
import java.text.SimpleDateFormat

object StringDateComparator : Comparator<Assignment> {
    @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("dd-MMM-yyyy")

    override fun compare(o1: Assignment, o2: Assignment): Int {
        val date1 = sdf.parse(o1.lastDate)
        val date2 = sdf.parse(o2.lastDate)

        return when {
            date1!!.after(date2) -> 1
            date2!!.after(date1) -> -1
            else -> 0
        }
    }
}