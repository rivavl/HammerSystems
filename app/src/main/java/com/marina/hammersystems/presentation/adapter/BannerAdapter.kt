package com.marina.hammersystems.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marina.hammersystems.databinding.PromoItemBinding

class BannerAdapter :
    ListAdapter<Int, BannerAdapter.BannerViewHolder>(BannerDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = PromoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = getItem(position)
        val context = holder.binding.root.context
        with(holder) {
            loadImage(banner, binding.ivBanner, context)
        }
    }

    private fun loadImage(url: Int, imageView: AppCompatImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    class BannerViewHolder(val binding: PromoItemBinding) : RecyclerView.ViewHolder(binding.root)

    class BannerDiffUtilCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }
}