package com.example.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposebasics.flows.KotlinFlows
import com.example.jetpackcomposebasics.httpRequests.HttpScreen
import com.example.jetpackcomposebasics.login.LoginScreen
import com.example.jetpackcomposebasics.login.LoginViewModel
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: LoginViewModel by viewModels()
        setContent {
            JetpackComposeBasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("basics") { BasicsScreen(navController) }
                        composable("counter_app") {
                            CounterApp(navController)
                        }
                        composable("counter_app/{count_val}") {backStackEntry ->
                            val defaultCount = backStackEntry.arguments?.getString("count_val")
                                ?.toInt()
                            CounterApp(navController, defaultCount = defaultCount ?: 0)
                        }
                        composable("todo") { TodoAPP(navController) }
                        composable("login") { LoginScreen(navController, viewModel) }
                        composable("flows") { KotlinFlows() }
                        composable("http") { HttpScreen() }
                    }

                }
            }
        }
    }
}
