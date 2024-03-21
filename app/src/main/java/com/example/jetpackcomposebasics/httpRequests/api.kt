package com.example.jetpackcomposebasics.httpRequests

import com.example.jetpackcomposebasics.httpRequests.model.AuthResponse
import com.example.jetpackcomposebasics.httpRequests.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface api {

    // Example
//    @GET("/products/{id}")
//    suspend fun getProducts(
//        @Path("id") product_id : Long,
//        @Query("page") page : Int,
//        @Query("filter") filter : String
//    ) : Any

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>

    @POST("/user")
    suspend fun postUser(@Body user : User) : Response<User>

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path("id") userId : Long) : Response<AuthResponse>

}

