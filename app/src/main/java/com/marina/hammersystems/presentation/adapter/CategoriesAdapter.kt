package com.marina.hammersystems.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marina.hammersystems.databinding.CategoryItemBinding
import com.marina.hammersystems.presentation.entity.CategoryUI

class CategoriesAdapter :
    ListAdapter<CategoryUI, CategoriesAdapter.CategoryItemViewHolder>(CategoryDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding = CategoryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = getItem(position)
        holder.binding.categoryName.text = category.name
    }

    class CategoryItemViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CategoryDiffUtilCallback : DiffUtil.ItemCallback<CategoryUI>() {
        override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem == newItem
        }
    }
}