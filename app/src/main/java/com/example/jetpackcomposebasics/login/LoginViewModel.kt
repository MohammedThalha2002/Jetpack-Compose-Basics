package com.example.jetpackcomposebasics.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebasics.RealmApp
import com.example.jetpackcomposebasics.models.User
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val realm = RealmApp.realm

    // GET ALL USERS
    val users = realm
        .query<User>()
        .asFlow()
        .map { result ->
            result.list.toList()
        }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    // ADD NEW USER
    private fun addUser(user : User){
        viewModelScope.launch {
            realm.write {
                val newUser = User().apply {
                    name = user.name
                    password = user.password
                }
                copyToRealm(newUser)
            }
        }
    }

    // DELETE A USER
    fun deleteUser(user : User){
        viewModelScope.launch {
            realm.write {
                val latestUser = findLatest(user) ?: return@write
                delete(latestUser)
            }
        }
    }

    // SIGN IN
    fun signIn(user: User){
        // 1. check if already a user with tha same username is exists
        // 2. if not then add the user to the db
        // 3. else throw an assertion error
        Log.d("DebugRecomposition", "Adding new user")
        viewModelScope.launch {
            realm.write {
                // 1
                val checkUser = query<User>(query = "name == $0", user.name).find()
                Log.d("DebugRecomposition", checkUser.toString())

                if(checkUser.size == 0){
                    // 2
                    addUser(user)
                    Log.d("DebugRecomposition", "New user added")
                } else{
                    // 3
                    Log.d("DebugRecomposition", "Same username already exists")
                }
            }
        }
    }

    fun login(user: User){
        // 1. find the user with the same username
        // 2. if not throw error - user not found
        // 3. else check for the password
        // 4. if doesn't match throw error

        viewModelScope.launch {
            realm.write {
                // 1
                val checkUser = query<User>(query = "name == $0", user.name).find()
                Log.d("DebugRecomposition", checkUser.toString())

                if(checkUser.size == 0){
                    // 2
                    Log.d("DebugRecomposition", "User not found")
                    return@write;
                }

                //3
                Log.d("DebugRecomposition", "Checking password")
                if (checkUser[0].password == user.password){
                    Log.d("DebugRecomposition", "Logged in successfully")
                } else{
                    Log.d("DebugRecomposition", "Invalid password")
                }

            }
        }
    }

}