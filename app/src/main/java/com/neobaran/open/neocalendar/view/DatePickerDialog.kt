package com.neobaran.open.neocalendar.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.DialogFragment
import com.neobaran.open.neocalendar.R
import com.neobaran.open.neocalendar.bean.DayBean
import com.neobaran.open.neocalendar.bean.MonthBean
import kotlinx.android.synthetic.main.date_picker_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*

class DatePickerDialog : DialogFragment() {

    private var cancelListener: ((dialog: DatePickerDialog) -> Unit)? = null
    private var okListener: ((dialog: DatePickerDialog, selectedDay: DayBean?) -> Unit)? = null

    fun setCancelClickListener(l: (dialog: DatePickerDialog) -> Unit) {
        cancelListener = l
    }

    fun setOkClickListener(l: (dialog: DatePickerDialog, selectedDay: DayBean?) -> Unit) {
        okListener = l
    }

    var selectedMonth: MonthBean? = null
    var selectedDay: DayBean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.date_picker_dialog, container, false).apply {

            month_view.setMonthSelectedListener {
                selectedMonth = it
                month_year.text = resources.getStringArray(R.array.month_list)[it.month]
                show_year.text = String.format(getString(R.string.show_year), it.year)
            }

            month_view.setDaySelectedListener {
                selectedDay = it
                val time = Calendar.getInstance().apply {
                    set(Calendar.YEAR, it.year)
                    set(Calendar.MONTH, it.month)
                    set(Calendar.DAY_OF_MONTH, it.day)
                }.time
                show_date.text = SimpleDateFormat(
                    getString(R.string.show_date),
                    ConfigurationCompat.getLocales(resources.configuration)[0]
                ).format(time)
            }

            month_view.initData()

            arrow_left.setOnClickListener {
                month_view.prevMonth()
            }
            arrow_right.setOnClickListener {
                month_view.nextMonth()
            }
            btn_cancel.setOnClickListener {
                cancelListener?.invoke(this@DatePickerDialog)
            }
            btn_ok.setOnClickListener {
                okListener?.invoke(this@DatePickerDialog, selectedDay)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        with(dialog?.window) {
            this?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            this?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

}