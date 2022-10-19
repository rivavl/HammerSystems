package com.marina.hammersystems.di.module

import android.app.Application
import com.marina.hammersystems.data.repository.ProductsRepositoryImpl
import com.marina.hammersystems.data.source.local.AppDatabase
import com.marina.hammersystems.data.source.local.ProductsDao
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
    fun bindProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository

    companion object {
        @Provides
        fun provideProductsApi(retrofit: RetrofitInstance): ProductsApi {
            return retrofit.productsApi
        }

        @Provides
        fun provideProductsDao(database: AppDatabase): ProductsDao {
            return database.productsDao()
        }

        @ApplicationScope
        @Provides
        fun provideDatabase(context: Application): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        @ApplicationScope
        @Provides
        fun provideRetrofitInstance(): RetrofitInstance {
            return RetrofitInstance
        }
    }

}