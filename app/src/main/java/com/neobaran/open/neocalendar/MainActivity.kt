package com.neobaran.open.neocalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        date_list.setMonthSelectedListener {
            Log.i("test", "m $it")
        }
        date_list.initData()


    }
}
