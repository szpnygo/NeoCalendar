package com.neobaran.open.neocalendar.adapter

import androidx.recyclerview.widget.DiffUtil
import com.neobaran.open.neocalendar.bean.DayBean

class DayDiffCallback : DiffUtil.ItemCallback<DayBean>() {

    override fun areItemsTheSame(oldItem: DayBean, newItem: DayBean): Boolean {
        return oldItem.date() == newItem.date()
    }

    override fun areContentsTheSame(oldItem: DayBean, newItem: DayBean): Boolean {
        return oldItem.date() == newItem.date()
    }
}