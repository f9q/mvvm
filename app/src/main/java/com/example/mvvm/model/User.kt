package com.example.mvvm.model

class User {
    var name    =   "TEST-"
    var age     =   0

    override fun toString(): String {
        return "$name-$age"
    }
}