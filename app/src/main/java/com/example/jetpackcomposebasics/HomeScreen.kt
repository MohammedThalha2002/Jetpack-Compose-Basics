package com.example.jetpackcomposebasics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpackcomposebasics.flows.FlowViewModel

@Composable
fun HomeScreen(navController: NavHostController){

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("basics")
        }) {
            Text(text = "BASICS")
        }
        Button(onClick = {
            navController.navigate("counter_app")
        }) {
            Text(text = "Counter APP")
        }
        Button(onClick = {
            navController.navigate("flows")
        }) {
            Text(text = "KOTLIN FLOWS")
        }
        Button(onClick = {
            navController.navigate("todo")
        }) {
            Text(text = "TODO APP")
        }
        Button(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "LOGIN APP")
        }
        Button(onClick = {
            navController.navigate("http")
        }) {
            Text(text = "HTTP REQUESTS")
        }
    }
}