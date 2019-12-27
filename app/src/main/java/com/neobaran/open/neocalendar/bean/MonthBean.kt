package com.neobaran.open.neocalendar.bean

import java.io.Serializable

class MonthBean(val year: Int, val month: Int) : Serializable {

    fun createNextMonth(): MonthBean {
        var nextMonth = this.month
        var nextYear = this.year
        nextMonth++
        if (nextMonth == 12) {
            nextMonth = 0
            nextYear++
        }
        return MonthBean(nextYear, nextMonth)
    }

    fun createPrevMonth(): MonthBean {
        var nextMonth = this.month
        var nextYear = this.year
        nextMonth--
        if (nextMonth == -1) {
            nextMonth = 11
            nextYear--
        }
        return MonthBean(nextYear, nextMonth)
    }

    override fun toString(): String {
        return "$year-$month"
    }
}