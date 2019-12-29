package com.neobaran.open.neocalendar

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.neobaran.open.neocalendar.bean.DayBean
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarMonthUtil(
    private val context: Context,
    private val year: Int,
    private val month: Int
) {

    private val calendar =
        Calendar.getInstance(ConfigurationCompat.getLocales(context.resources.configuration)[0])
            .apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, 1)
            }

    init {
        calendar.set(Calendar.DAY_OF_MONTH, 1)

    }

    private fun getMonthDay(): Int {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun getStartOfMonth(): Int {
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    private fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
    }

    private fun prevMonth() {
        calendar.add(Calendar.MONTH, -1)
    }

    @Suppress("UNCHECKED_CAST")
    fun createMonthDayList(): ArrayList<DayBean> {
        val dayList = arrayListOf<DayBean>()
        dayList.clear()
        //添加标题行
        for (i in 0 until 7) {
            dayList.add(DayBean(getMonthWeekTitle(i)))
        }

        //添加前置空天数
        val startOfMonth = getStartOfMonth()
        prevMonth()
        val lastMonthMaxDay = getMonthDay()
        for (i in 1 until startOfMonth) {
            dayList.add(
                DayBean(
                    calendar.year(),
                    calendar.month(),
                    lastMonthMaxDay - startOfMonth + 1 + i,
                    -1
                )
            )
        }
        nextMonth()

        //添加天
        for (i in 0 until getMonthDay()) {
            dayList.add(DayBean(calendar.year(), calendar.month(), i + 1))
        }

        //添加下个月的补天
        nextMonth()
        val nextMonthDay = 49 - dayList.size
        for (i in 0 until nextMonthDay) {
            dayList.add(DayBean(calendar.year(), calendar.month(), i + 1, 1))
        }
        prevMonth()
        return dayList
    }

    private fun getMonthWeekTitle(week: Int): String {
        return context.resources.getStringArray(R.array.week_title_list)[week]
    }

}

fun Calendar.year(): Int {
    return get(Calendar.YEAR)
}

fun Calendar.month(): Int {
    return get(Calendar.MONTH)
}

fun Calendar.day(): Int {
    return get(Calendar.DAY_OF_MONTH)
}

fun Calendar.formatStr(format: String, locale: Locale = Locale.getDefault()): String {
    return SimpleDateFormat(format, locale).format(this.time)
}
