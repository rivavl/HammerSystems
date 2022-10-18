package com.marina.hammersystems.data.mapper

import com.marina.hammersystems.data.source.remote.dto.products.Product
import com.marina.hammersystems.domain.entity.ProductEntity

fun List<Product>.toDomain(): List<ProductEntity> {
    return map {
        it.toDomain()
    }
}

fun Product.toDomain(): ProductEntity {
    return ProductEntity(
        id = id,
        description = description,
        imageUrl = imageUrl,
        name = name,
        price = price ?: "500"
    )
}