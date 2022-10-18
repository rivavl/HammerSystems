package com.marina.hammersystems.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.hammersystems.R
import com.marina.hammersystems.presentation.fragment.PizzaListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_view, PizzaListFragment())
            .commit()
    }
}