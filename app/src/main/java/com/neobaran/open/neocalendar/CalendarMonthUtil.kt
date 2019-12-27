package com.neobaran.open.neocalendar

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.neobaran.open.neocalendar.bean.DayBean
import java.util.*
import kotlin.collections.ArrayList

class CalendarMonthUtil(private val context: Context) {

    private val calendar =
        Calendar.getInstance(ConfigurationCompat.getLocales(context.resources.configuration)[0])
            .apply {
                set(Calendar.DAY_OF_MONTH, 1)
                add(Calendar.MONTH, -2)
            }

    private val dayList = arrayListOf<DayBean>()

    private fun getMonthDay(): Int {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun getStartOfMonth(): Int {
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    fun createMonthDayList() {
        dayList.clear()
        //添加标题行
        for (i in 0 until 7) {
            dayList.add(DayBean(getMonthWeekTitle(i)))
        }
        //添加前置空天数
        for (i in 0 until getStartOfMonth() - 1) {
            dayList.add(DayBean())
        }
        //添加天
        for (i in 0 until getMonthDay()) {
            dayList.add(DayBean(calendar.year(), calendar.month(), i + 1))
        }
    }

    fun getMonthDayList(): ArrayList<DayBean> {
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