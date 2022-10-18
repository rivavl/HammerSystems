package com.marina.hammersystems.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marina.hammersystems.databinding.ProductItemBinding
import com.marina.hammersystems.presentation.entity.ProductUI

class ProductListAdapter :
    ListAdapter<ProductUI, ProductListAdapter.ProductItemViewHolder>(ProductDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = ProductItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = getItem(position)
        val context = holder.binding.root.context
        with(holder.binding) {
            product.imageUrl?.let { loadImage(it, ivProduct, context) }
            tvTitle.text = product.name
            tvDescription.text = product.description
            price.text = product.price
        }
    }

    private fun loadImage(url: String, imageView: AppCompatImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    class ProductItemViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUI>() {
        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }
    }
}