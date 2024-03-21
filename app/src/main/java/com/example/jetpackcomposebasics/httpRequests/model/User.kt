package com.example.jetpackcomposebasics.httpRequests.model

data class User(
    val userId : Long,
    val name : String,
    val password : String,
    val isVoted : Boolean = false
)