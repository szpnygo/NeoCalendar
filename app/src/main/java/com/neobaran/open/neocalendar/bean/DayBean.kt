package com.neobaran.open.neocalendar.bean

import com.neobaran.open.neocalendar.R
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class DayBean : Serializable {

    var calendar: Calendar = Calendar.getInstance()

    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    //是否最上方一行的标题
    var isTitle = false
    //显示内容
    var showData = ""

    //空日期
    var isEmptyDay = false

    constructor() {
        this.isEmptyDay = true
    }

    constructor(title: String) {
        this.isTitle = true
        this.showData = title
    }

    constructor(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
        this.showData = day.toString()
    }

    fun date(): String {
        return "$year-$month-$day"
    }

    //"yyyy-MM-dd"
    fun formatStr(format: String, locale: Locale = Locale.getDefault()): String {
        return SimpleDateFormat(format, locale).format(this.calendar.time)
    }


}