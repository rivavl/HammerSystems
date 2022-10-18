package com.marina.hammersystems.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.hammersystems.R
import com.marina.hammersystems.domain.use_case.GetCategoriesUseCase
import com.marina.hammersystems.domain.use_case.GetProductsListUseCase
import com.marina.hammersystems.domain.util.Resource
import com.marina.hammersystems.presentation.ErrorOnUI
import com.marina.hammersystems.presentation.UiState
import com.marina.hammersystems.presentation.entity.CategoryUI
import com.marina.hammersystems.presentation.entity.ProductUI
import com.marina.hammersystems.presentation.mapper.toUI
import kotlinx.coroutines.launch
import javax.inject.Inject

class PizzaListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsListUseCase: GetProductsListUseCase
) : ViewModel() {

    private var _bannersList = MutableLiveData<UiState<List<Int>>>()
    val bannersList: LiveData<UiState<List<Int>>> get() = _bannersList

    private var _categoriesList = MutableLiveData<UiState<List<CategoryUI>>>()
    val categoriesList: LiveData<UiState<List<CategoryUI>>> get() = _categoriesList

    private var _productsList = MutableLiveData<UiState<List<ProductUI>>>()
    val productsList: LiveData<UiState<List<ProductUI>>> get() = _productsList

    init {
        getBanners()
        getProducts()
        getCategories()
    }

    private fun getBanners() = viewModelScope.launch {
        _bannersList.postValue(
            UiState.Success(
                listOf(
                    R.mipmap.banner,
                    R.mipmap.banner,
                    R.mipmap.banner
                )
            )
        )
    }

    fun getCategories() = viewModelScope.launch {
        getCategoriesUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _categoriesList.postValue(UiState.Loading())
                }
                is Resource.Error -> {
                    _categoriesList.postValue(UiState.Error(ErrorOnUI.UNKNOWN))
                }
                is Resource.Success -> {
                    _categoriesList.postValue(UiState.Success(result.data?.toUI()!!))
                }
            }
        }
    }

    fun getProducts() = viewModelScope.launch {
        getProductsListUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _productsList.postValue(UiState.Loading())
                }
                is Resource.Error -> {
                    _productsList.postValue(UiState.Error(ErrorOnUI.UNKNOWN))
                }
                is Resource.Success -> {
                    _productsList.postValue(UiState.Success(result.data?.toUI()!!))
                }
            }
        }
    }
}