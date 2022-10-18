package com.marina.hammersystems.di.module

import androidx.lifecycle.ViewModel
import com.marina.hammersystems.di.annotations.ViewModelKey
import com.marina.hammersystems.presentation.view_model.PizzaListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PizzaListViewModel::class)
    fun bindPizzaListViewModel(viewModel: PizzaListViewModel): ViewModel
}