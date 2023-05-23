package com.example.openinapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class LinkViewHolder(itemView: View) : ViewHolder(itemView) {
    val logo = itemView.findViewById<ImageView>(R.id.iv_logo)
    val linkName = itemView.findViewById<TextView>(R.id.tv_link_name)
    val date = itemView.findViewById<TextView>(R.id.date)
    val tvLink = itemView.findViewById<TextView>(R.id.tv_link)
    val tvClick = itemView.findViewById<TextView>(R.id.tv_clicks)
}