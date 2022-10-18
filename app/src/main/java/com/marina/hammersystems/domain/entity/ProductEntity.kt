package com.marina.hammersystems.domain.entity

data class ProductEntity(
    val id: Int,
    val description: String?,
    val imageUrl: String?,
    val name: String,
    val price: String
)
