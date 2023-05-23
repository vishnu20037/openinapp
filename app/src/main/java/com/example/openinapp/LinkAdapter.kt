package com.example.openinapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide

class LinkAdapter(val topLinks: ArrayList<TopLink>) : Adapter<LinkViewHolder>() {
    //    private val topLinks: ArrayList<TopLink> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.link_item, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topLinks.size
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val topLink: TopLink = topLinks[position]
        holder.date.text = topLink.created_at
        holder.linkName.text = topLink.title
        Glide.with(holder.itemView.context).load(topLink.original_image).into(holder.logo)
        holder.tvClick.text = topLink.total_clicks.toString()
        holder.tvLink.text = topLink.smart_link
    }

    fun updateList(items: ArrayList<TopLink>) {
        topLinks.clear()
        topLinks.addAll(items)
        notifyDataSetChanged()
    }
}