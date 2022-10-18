package com.marina.hammersystems.di.module

import com.marina.hammersystems.data.repository.ProductsRepositoryImpl
import com.marina.hammersystems.data.source.remote.ProductsApi
import com.marina.hammersystems.data.source.remote.RetrofitInstance
import com.marina.hammersystems.di.annotations.ApplicationScope
import com.marina.hammersystems.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: ProductsRepositoryImpl): ProductsRepository

    companion object {
        @Provides
        fun provideProductsApi(): ProductsApi {
            return RetrofitInstance.productsApi
        }
    }

}