package com.neobaran.open.neocalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.neobaran.open.neocalendar.adapter.DayAdapter
import com.neobaran.open.neocalendar.bean.DayBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dayAdapter: DayAdapter = DayAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(date_list) {
            layoutManager = GridLayoutManager(context, 7)
            adapter = dayAdapter
        }

        var list = arrayListOf<DayBean>()

        for (i in 0 until 42) {
            list.add(DayBean())
        }

        val util = CalendarMonthUtil(this)
        util.createMonthDayList()

        dayAdapter.submitList(util.getMonthDayList())

    }
}
