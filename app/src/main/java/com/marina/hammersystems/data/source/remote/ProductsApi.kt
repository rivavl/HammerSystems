package com.marina.hammersystems.data.source.remote

import com.marina.hammersystems.data.source.remote.dto.categories.CategoriesResponse
import com.marina.hammersystems.data.source.remote.dto.products.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("productos")
    suspend fun getProductsList(): Response<ProductResponse>

    @GET("tags")
    suspend fun getCategories(): Response<CategoriesResponse>
}