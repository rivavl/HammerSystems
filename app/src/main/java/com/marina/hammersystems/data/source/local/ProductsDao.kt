package com.marina.hammersystems.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marina.hammersystems.data.source.local.entity.CategoryDB
import com.marina.hammersystems.data.source.local.entity.ProductDB

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<ProductDB>)

    @Query("select * from product")
    suspend fun getProducts(): List<ProductDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categories: List<CategoryDB>)

    @Query("select * from category")
    suspend fun getCategories(): List<CategoryDB>?
}
