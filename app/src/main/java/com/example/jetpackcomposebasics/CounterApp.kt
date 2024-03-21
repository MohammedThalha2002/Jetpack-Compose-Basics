package com.example.jetpackcomposebasics

import android.icu.text.ListFormatter.Width
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CounterApp(navController: NavHostController, defaultCount : Int = 0){
    // one way
//    var count : MutableState<Int> = remember {
//        mutableStateOf(0)
//    }
    // or
    var count by remember {
        mutableStateOf(defaultCount)
    }

    fun changeCount(type: String){
        if(type == "ADD"){
            count++;
        } else {
            count--;
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count = $count")
        Row {
            CounterButton(type = "ADD", cnt = count, changeCount = {
                Log.d("DebugRecomposition", it)
                changeCount(it);
            } )
            Spacer(modifier = Modifier.width(20.dp))
            CounterButton(type = "SUB", cnt = count, changeCount = {
                Log.d("DebugRecomposition", it)
                changeCount(it);
            })
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Navigate to counter app with a default count value of 100",
            textAlign = TextAlign.Center)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // NAVIGATE BUTTON
            Button(onClick = {
                navController.navigate("counter_app/101")
            }) {
                Text(text = "navigate")
            }
            // BACK BUTTON
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun CounterButton(type : String, cnt : Int, changeCount : (String) -> Unit){
    if(type == "ADD"){
        Button(onClick = {
            changeCount(type)
        }, enabled = cnt < 10) {
            Text(text = "+")
        }
    } else {
        Button(onClick = {
            changeCount(type)
        }, enabled = cnt > 0) {
            Text(text = "-")
        }
    }
}