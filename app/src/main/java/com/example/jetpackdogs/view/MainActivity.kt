package com.example.jetpackdogs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.jetpackdogs.R
import com.example.jetpackdogs.Utils.NavigationUtil

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController =NavigationUtil.setupBackButtonOnToolbar(this,R.id.fragment2)
    }

    override fun onSupportNavigateUp(): Boolean { // Setting the nav controller back button.
        return NavigationUtil.enableNavigationUp(navController)
    }
}