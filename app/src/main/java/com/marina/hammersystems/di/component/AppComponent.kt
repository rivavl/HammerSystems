package com.marina.hammersystems.di.component

import android.app.Application
import com.marina.hammersystems.di.annotations.ApplicationScope
import com.marina.hammersystems.presentation.fragment.PizzaDetailFragment
import com.marina.hammersystems.presentation.fragment.PizzaListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component()
interface AppComponent {

    fun inject(fragment: PizzaListFragment)
    fun inject(fragment: PizzaDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}