package com.marina.hammersystems.presentation.mapper

import com.marina.hammersystems.domain.entity.ProductEntity
import com.marina.hammersystems.presentation.entity.ProductUI

fun List<ProductEntity>.toUI(): List<ProductUI> {
    return map {
        it.toUI()
    }
}

fun ProductEntity.toUI(): ProductUI {
    return ProductUI(
        id = id,
        description = description,
        imageUrl = imageUrl,
        name = name,
        price = price
    )
}