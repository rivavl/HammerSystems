package com.marina.hammersystems.data.repository

import android.util.Log
import com.marina.hammersystems.data.mapper.toDomain
import com.marina.hammersystems.data.source.remote.ProductsApi
import com.marina.hammersystems.domain.entity.CategoryEntity
import com.marina.hammersystems.domain.entity.ProductEntity
import com.marina.hammersystems.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsRepository {

    override suspend fun getProductsList(): List<ProductEntity>? {
        return productsApi.getProductsList().body()?.products?.toDomain()
    }

    override suspend fun getCategories(): List<CategoryEntity>? {
        return productsApi.getCategories().body()?.categories?.toDomain()
    }
}