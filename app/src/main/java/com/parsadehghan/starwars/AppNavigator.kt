package com.parsadehghan.starwars

import android.os.Bundle
import androidx.navigation.NavController
import com.parsadehghan.navigator.Navigator

class AppNavigator(private val navController: NavController) : Navigator {
    override fun navigateTo(destination: String, args: Bundle?) {
        navController.navigate(destination)
    }
}
