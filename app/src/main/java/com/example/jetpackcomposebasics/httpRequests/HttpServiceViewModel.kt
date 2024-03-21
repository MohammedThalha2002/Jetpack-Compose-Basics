package com.example.jetpackcomposebasics.httpRequests

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebasics.httpRequests.model.User
import kotlinx.coroutines.launch

class HttpServiceViewModel : ViewModel() {

    var users : MutableList<User> = mutableStateListOf()

    init {
        getUsersList()
    }

    fun getUsersList() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUsers()
            if(response.isSuccessful){
                Log.d("GET REQ", response.body()!!.toString())
                users.clear()
                users.addAll(response.body()!!)
                Log.d("USERS", users.toList().toString())
            }
        }
    }

    fun postUser(user : User){
        viewModelScope.launch {
            val response = RetrofitInstance.api.postUser(user)
            if(response.isSuccessful){
                getUsersList()
                Log.d("RESPONSE", response.body()!!.toString())
            } else {
                Log.d("RESPONSE", response.errorBody().toString())
            }
        }
    }

    fun deleteUser(userId : Long){
        viewModelScope.launch {
            val response = RetrofitInstance.api.deleteUser(userId)
            if(response.isSuccessful){
                getUsersList()
                Log.d("RESPONSE", response.body()!!.toString())
            } else {
                Log.d("ERROR", response.errorBody().toString())
            }
        }
    }


}