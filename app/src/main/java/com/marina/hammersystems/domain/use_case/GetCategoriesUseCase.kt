package com.marina.hammersystems.domain.use_case

import com.marina.hammersystems.domain.entity.CategoryEntity
import com.marina.hammersystems.domain.repository.ProductsRepository
import com.marina.hammersystems.domain.util.ErrorType
import com.marina.hammersystems.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CategoryEntity>?>> = flow {
        try {
            emit(Resource.Loading())
            val categoriesList = repository.getCategories()
            emit(Resource.Success(categoriesList))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.INTERNET_CONNECTION))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.UNKNOWN))
        }
    }
}