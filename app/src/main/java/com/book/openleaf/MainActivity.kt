package com.book.openleaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.book.openleaf.Pages.HomeScreen
import com.book.openleaf.Pages.SplashScreen
import com.book.openleaf.ui.theme.OpenLeafTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenLeafTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "SplashScreen",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("SplashScreen") { SplashScreen(navHostController = navController) }
                        composable("HomeScreen") { HomeScreen(navHostController = navController) }
                    }
                }
            }
        }
    }
}