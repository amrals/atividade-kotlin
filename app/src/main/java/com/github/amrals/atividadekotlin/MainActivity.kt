package com.github.amrals.atividadekotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.amrals.atividadekotlin.screens.LoginScreen
import com.github.amrals.atividadekotlin.screens.MenuScreen
import com.github.amrals.atividadekotlin.screens.PedidosScreen
import com.github.amrals.atividadekotlin.screens.PerfilScreen
import com.github.amrals.atividadekotlin.ui.theme.AtividadekotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtividadekotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(
                            route = "pedidos",
                        ) {
                            PedidosScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                            )
                        }
                        composable(
                            route = "perfil",
                        ) {
                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                            )
                        }
                    }
                }
            }
        }
    }
}