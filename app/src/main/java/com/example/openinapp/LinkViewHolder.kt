package com.example.openinapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.openinapp.databinding.LinkItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class LinkViewHolder(private val binding: LinkItemBinding) : ViewHolder(binding.root) {
    fun bind(topLink: TopLink) {
        val formattedDate = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        ).parse(topLink.created_at)
            ?.let {
                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(
                    it
                )
            }
        Glide.with(binding.root.context).load(topLink.original_image).into(binding.ivLogo)
        binding.date.text = formattedDate
        binding.tvClicks.text = topLink.total_clicks.toString()
        binding.linkData = topLink
        binding.executePendingBindings()
    }
}