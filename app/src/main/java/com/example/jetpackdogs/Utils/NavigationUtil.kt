package com.example.jetpackdogs.Utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

object NavigationUtil {

    fun setupBackButtonOnToolbar(activity: AppCompatActivity, container: Int): NavController {
        val navController = Navigation.findNavController(activity, container)
        NavigationUI.setupActionBarWithNavController(activity, navController)
        return navController
    }

    fun enableNavigationUp(navController: NavController) =
        NavigationUI.navigateUp(navController, null)

    fun navigationTransaction(view: View, navDirection: NavDirections) =
        Navigation.findNavController(view).navigate(navDirection)

}