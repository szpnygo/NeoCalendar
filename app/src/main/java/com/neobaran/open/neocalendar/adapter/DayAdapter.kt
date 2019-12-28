package com.neobaran.open.neocalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neobaran.open.neocalendar.R
import com.neobaran.open.neocalendar.bean.DayBean
import kotlinx.android.synthetic.main.item_day.view.*

class DayAdapter(private val context: Context) :
    ListAdapter<DayBean, DayAdapter.ItemViewHolder>(DayDiffCallback()) {

    private var selectedDay: DayBean? = null

    fun setSelectedDay(d: DayBean?) {
        selectedDay = d
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val day = getItem(position)
        with(holder.itemView) {
            date_day.text = day.showData
            if (day.type == 0) {
                date_day.setTextColor(ContextCompat.getColor(context, R.color.text_main))
            } else {
                date_day.setTextColor(ContextCompat.getColor(context, R.color.text_disable))
            }

            if (day == selectedDay) {
                date_day.text = "T"
            }
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

}