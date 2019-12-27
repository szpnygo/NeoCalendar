package com.neobaran.open.neocalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.ConfigurationCompat
import androidx.viewpager2.widget.ViewPager2
import com.neobaran.open.neocalendar.adapter.MonthAdapter
import com.neobaran.open.neocalendar.bean.MonthBean
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf<MonthBean>()
    private val adaper = MonthAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(date_list) {
            adapter = adaper
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.i("test", "select :$position")
                }
            })
        }
        val cal = Calendar.getInstance(ConfigurationCompat.getLocales(resources.configuration)[0])
        cal.add(Calendar.MONTH, -36)

        for (i in 0 until 42) {
            val item = MonthBean(cal.year(), cal.month())
            Log.i("test", item.toString())
            list.add(item)
            cal.add(Calendar.MONTH, 1)
        }
        adaper.notifyDataSetChanged()
        date_list.setCurrentItem(36, false)

        date_list.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == list.size - 1) {
                    list.add(list[position].createNextMonth())
                    adaper.notifyItemChanged(position + 1)
                } else if (position == 0) {
                    list.add(0, list[position].createPrevMonth())
                    adaper.notifyDataSetChanged()
                    date_list.setCurrentItem(1, false)
                }

                val currentMonth = list[position]
                cal.set(Calendar.YEAR, currentMonth.year)
                cal.set(Calendar.MONTH, currentMonth.month)
                month_year.text = resources.getStringArray(R.array.month_list)[currentMonth.month]
                show_year.text = String.format(getString(R.string.show_year), currentMonth.year)
            }
        })

        val currentMonth = list[36]
        month_year.text = resources.getStringArray(R.array.month_list)[currentMonth.month]
        show_year.text = String.format(getString(R.string.show_year), currentMonth.year)
    }
}
