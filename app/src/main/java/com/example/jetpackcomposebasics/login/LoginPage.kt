package com.example.jetpackcomposebasics.login

import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.example.jetpackcomposebasics.models.User

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel){
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val users by viewModel.users.collectAsState()


    val context = LocalContext.current

    fun onSubmit(type : String){
        if(username == "" || password == ""){
            Toast.makeText(context, "Username and Password should not be empty",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        val newUser = User().apply {
            name = username
            this.password = password
        }
        if(type == "SignUp"){
            viewModel.signIn(newUser)
        } else {
            viewModel.login(newUser)
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn{
            items(users) {user ->
                ClickableText(text = AnnotatedString(user.name), onClick = {
                    viewModel.deleteUser(user)
                })
            }
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "BACK")
            }
            Button(onClick = {
                onSubmit("SignUp")
            }) {
                Text(text = "SignUp")
            }
            Button(onClick = {
                onSubmit("login")
            }) {
                Text(text = "LOGIN")
            }
        }
    }

}
