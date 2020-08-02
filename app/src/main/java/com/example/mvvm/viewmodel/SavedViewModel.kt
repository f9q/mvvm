package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User

class SavedViewModel (val savedStateHandle: SavedStateHandle) : ViewModel(){


    fun loadUser() : LiveData<User> {
        return savedStateHandle.getLiveData<User>("User")
    }
}