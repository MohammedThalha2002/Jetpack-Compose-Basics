package com.example.jetpackcomposebasics.httpRequests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposebasics.httpRequests.model.User

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HttpScreen(){
    val httpServiceViewModel : HttpServiceViewModel = viewModel<HttpServiceViewModel>()

    val usersList = httpServiceViewModel.users
    
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(modifier = Modifier.padding(8.dp)) {
            usersList.forEach{user->
                Button(onClick = {
                    httpServiceViewModel.deleteUser(user.userId)
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(text = user.name)
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            httpServiceViewModel.postUser(User(name = "HELLO", password = "12345", userId = 3))
            httpServiceViewModel.getUsersList()
        }) {
            Text(text = "POST A NEW USER")
        }

    }
}