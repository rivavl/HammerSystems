package com.marina.hammersystems.app

import android.app.Application
import com.marina.hammersystems.di.component.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}