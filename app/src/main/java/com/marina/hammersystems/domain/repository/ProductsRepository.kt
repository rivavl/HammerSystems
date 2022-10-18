package com.marina.hammersystems.domain.repository

import com.marina.hammersystems.domain.entity.CategoryEntity
import com.marina.hammersystems.domain.entity.ProductEntity

interface ProductsRepository {

    suspend fun getProductsList(): List<ProductEntity>?

    suspend fun getCategories(): List<CategoryEntity>?
}