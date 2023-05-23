package com.example.openinapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.openinapp.databinding.LinkItemBinding

class LinkAdapter(val topLinks: ArrayList<TopLink>) : Adapter<LinkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LinkItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.link_item,
            parent,
            false
        )
        return LinkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return topLinks.size
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val topLink: TopLink = topLinks[position]
        holder.bind(topLink)
    }

}