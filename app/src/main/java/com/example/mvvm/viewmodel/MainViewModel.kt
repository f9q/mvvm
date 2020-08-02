package com.example.mvvm.viewmodel

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import com.example.mvvm.model.UserModel
import java.util.*

class MainViewModel : ViewModel{

    constructor():super(){
        loadUser()
    }
    var task        :   AsyncTask<Void, User,Unit>? = null
    var timer       :   Timer?                      = null
    var userModel   :   UserModel                   = UserModel()

    fun loadUser() : LiveData<User>{
        val ud = userModel.userFromServer()
        return ud
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("MainViewModel","onCleared")
        cancelTask()
    }


    //1.MutableLiveData
    fun test1(){

        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                val userData = userModel.userFromServer()
                val user = userData.value
                val age = user!!.age + 1
                var name = user.name.substringBefore("-")
                name = "$name-$age"
                userModel.modifyUser(age,name)
            }

        },1000 * 3,1000 * 3)
    }

    fun test2(){
        val task = object : AsyncTask<Void, User,Unit>(){
            override fun doInBackground(vararg params: Void?) {
                timer = Timer()
                timer?.schedule(object : TimerTask(){
                    override fun run() {
                        val userData = userModel.userFromServer()
                        val user = userData.value
                        val age = user!!.age + 1
                        val name = user?.name?.substringBefore("-")
                        user?.age = age
                        user?.name="$name-$age"

                        Log.e("MainViewModel","view model running , i = $age")
                        publishProgress(user)
                    }

                },1000 * 1,1000 * 1)
            }

            override fun onProgressUpdate(vararg values: User?) {
                super.onProgressUpdate(*values)
                val v = values[0]
                val name = v?.name
                val age = v?.age
                userModel.modifyUser(age!!,name!!)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
            }

            override fun onCancelled() {
                super.onCancelled()
            }

            override fun onPreExecute() {
                super.onPreExecute()
            }
        }
        task.execute()
    }
    fun cancelTask(){
        Log.e("MainViewModel","cancelTask")

        timer?.cancel()
        timer = null
        task?.cancel(true)
    }
}