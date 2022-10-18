package com.marina.hammersystems.domain.use_case

import com.marina.hammersystems.domain.entity.ProductEntity
import com.marina.hammersystems.domain.repository.ProductsRepository
import com.marina.hammersystems.domain.util.ErrorType
import com.marina.hammersystems.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ProductEntity>?>> = flow {
        try {
            emit(Resource.Loading())
            val productsList = repository.getProductsList()
            emit(Resource.Success(productsList))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.INTERNET_CONNECTION))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.UNKNOWN))
        }
    }
}