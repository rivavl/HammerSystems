package com.marina.hammersystems.data.mapper

import com.marina.hammersystems.data.source.local.entity.ProductDB
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

fun List<ProductDB>.toEntity(): List<ProductEntity> {
    return map {
        it.toDomain()
    }
}

fun ProductDB.toDomain(): ProductEntity {
    return ProductEntity(
        id = id,
        description = description,
        imageUrl = imageUrl,
        name = name,
        price = price ?: "500"
    )
}

fun List<Product>.toDB(): List<ProductDB> {
    return map {
        it.toDB()
    }
}

fun Product.toDB(): ProductDB {
    return ProductDB(
        id = id,
        description = description,
        imageUrl = imageUrl,
        name = name,
        price = price ?: "500"
    )
}