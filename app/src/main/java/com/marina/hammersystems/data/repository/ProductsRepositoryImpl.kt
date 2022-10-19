package com.marina.hammersystems.data.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.marina.hammersystems.data.mapper.toDB
import com.marina.hammersystems.data.mapper.toDomain
import com.marina.hammersystems.data.mapper.toEntity
import com.marina.hammersystems.data.source.local.ProductsDao
import com.marina.hammersystems.data.source.remote.ProductsApi
import com.marina.hammersystems.data.source.remote.dto.categories.Category
import com.marina.hammersystems.data.source.remote.dto.products.Product
import com.marina.hammersystems.domain.entity.CategoryEntity
import com.marina.hammersystems.domain.entity.ProductEntity
import com.marina.hammersystems.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
    private val dao: ProductsDao,
    private val context: Application
) : ProductsRepository {

    override suspend fun getProductsList(): List<ProductEntity>? {
        if (hasConnection()) {
            val response = productsApi.getProductsList()
            if (response.body() != null) {
                saveProducts(response.body()?.products!!)
            } else {
                return getProductsFromDB()
            }
            return response.body()?.products?.toDomain()
        } else {
            return getProductsFromDB()
        }
    }

    override suspend fun getCategories(): List<CategoryEntity>? {
        if (hasConnection()) {
            val response = productsApi.getCategories()
            if (response.isSuccessful) {
                saveCategories(response.body()?.categories!!)
            } else {
                return getCategoriesFromDB()
            }
            return response.body()?.categories?.toDomain()
        } else {
            return getCategoriesFromDB()
        }
    }

    private fun saveProducts(products: List<Product>) = CoroutineScope(Dispatchers.IO).launch {
        dao.saveProducts(products.toDB())
    }

    private fun saveCategories(categories: List<Category>) = CoroutineScope(Dispatchers.IO).launch {
        dao.saveCategories(categories.toDB())
    }

    private suspend fun getProductsFromDB(): List<ProductEntity>? {
        val a = dao.getProducts()?.toEntity()
        return a
    }

    private suspend fun getCategoriesFromDB(): List<CategoryEntity>? {
        val a = dao.getCategories()?.toEntity()
        return a
    }

    private fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null
    }
}