package com.neobaran.open.neocalendar.bean

import java.io.Serializable

class DayBean : Serializable {

    var year = 1970
    var month = 0
    var day = 1

    //是否最上方一行的标题
    var isTitle = false
    //显示内容
    var showData = ""

    var type = 0 //0 current month -1 last month 1 next month

    constructor(title: String) {
        this.isTitle = true
        this.showData = title
    }

    constructor(year: Int, month: Int, day: Int, type: Int = 0) {
        this.year = year
        this.month = month
        this.day = day
        this.showData = day.toString()
        this.type = type
    }

    fun update(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
    }

    fun date(): String {
        return "$year-$month-$day"
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other is DayBean) {
            return year == other.year && month == other.month && day == other.day
        }
        return false
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + day
        result = 31 * result + isTitle.hashCode()
        result = 31 * result + showData.hashCode()
        result = 31 * result + type
        return result
    }
}