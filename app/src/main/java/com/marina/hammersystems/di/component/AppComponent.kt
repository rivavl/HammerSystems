package com.marina.hammersystems.di.component

import android.app.Application
import com.marina.hammersystems.di.annotations.ApplicationScope
import com.marina.hammersystems.di.module.DataModule
import com.marina.hammersystems.di.module.ViewModelModule
import com.marina.hammersystems.presentation.fragment.PizzaListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: PizzaListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}