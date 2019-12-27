package com.neobaran.open.neocalendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neobaran.open.neocalendar.R
import com.neobaran.open.neocalendar.bean.MonthBean
import kotlinx.android.synthetic.main.item_month.view.*

class MonthAdapter(private var list: List<MonthBean>) :
    RecyclerView.Adapter<MonthAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_month,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
        with(holder.itemView) {
            month_view.initData(data.year, data.month)
        }

    }

}