package com.example.premierleagueapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.premierleagueapp.core.databinding.ItemListImageBinding

class ImageAdapter(private val imageUrls:List<String>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemListImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl)
    }

    inner class ImageViewHolder (private val binding: ItemListImageBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(imageUrl: String) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .into(ivImage)
            }
        }

    }
}