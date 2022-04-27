package com.slicedwork.slicedwork.util

import java.util.*


private fun isLeapYear(year: Int): Boolean {
    if (year % 4 != 0) return false
    if (year % 100 == 0) return false

    return true
}

fun getLastDayOfTheMonth(month: Int = 0, year: Int): Int {
    return when (month) {
        0, 2, 4, 6, 7, 9, 11 -> 31
        3, 5, 8, 10 -> 30
        else -> if (isLeapYear(year)) 29 else 28
    }
}

fun getDay(): Int = Calendar.getInstance()[Calendar.DAY_OF_MONTH]

fun getMonth(): Int = Calendar.getInstance()[Calendar.MONTH]

fun getYear(): Int = Calendar.getInstance()[Calendar.YEAR]
