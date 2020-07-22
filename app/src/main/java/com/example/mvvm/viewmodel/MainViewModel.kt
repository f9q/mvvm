package com.example.mvvm.viewmodel

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import java.util.*

class MainViewModel : ViewModel{
    constructor():super()

    var userData    =   MediatorLiveData<User>()
    var task        :   AsyncTask<Void, User,Unit>? = null
    var timer       :   Timer?                      = null

    init{
        userData.value = User()
    }

    //1.MutableLiveData
    fun test1(){

        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                val user = userData.value
                val age = user!!.age + 1
                val name = user.name.substringBefore("-")
                user.age = age
                user.name="$name-$age"
                userData.value = user
            }

        },1000 * 3,1000 * 3)
    }

    fun test2(){
        val task = object : AsyncTask<Void, User,Unit>(){
            override fun doInBackground(vararg params: Void?) {
                timer = Timer()
                timer?.schedule(object : TimerTask(){
                    override fun run() {
                        val user = userData.value
                        var age = user!!.age + 1
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
                userData.value = v
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
        timer?.cancel()
        timer = null
        task?.cancel(true)
    }
}