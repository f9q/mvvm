package com.example.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class UserModel {

    var userData    =   MediatorLiveData<User>()
    
    init {
        val user = User()
        userData.value = user
    }

    fun userFromSql() : LiveData<User> {
        return userData
    }
    fun userFromServer() : LiveData<User>{
        return userData
    }
    fun userFromFile() : LiveData<User>{
        return userData
    }
    fun userFromCache() : LiveData<User>{
        return userData
    }

    fun modifyUser(age : Int,name : String){
        val user = userData.value
        user?.age = age
        user?.name = name

        userData.value = user
    }


}