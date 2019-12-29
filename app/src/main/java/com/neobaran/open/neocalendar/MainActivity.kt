package com.neobaran.open.neocalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neobaran.open.neocalendar.view.DatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//
//        date_list.setMonthSelectedListener {
//            Log.i("test", "m $it")
//        }
//        date_list.initData()

        testclick.setOnClickListener {
            val dialog = DatePickerDialog()
            dialog.setCancelClickListener {
                it.dismiss()
            }
            dialog.setOkClickListener { _, selectedDay ->

                Log.i("test", "ok $selectedDay")
            }
            dialog.show(supportFragmentManager, "")
        }
    }
}
