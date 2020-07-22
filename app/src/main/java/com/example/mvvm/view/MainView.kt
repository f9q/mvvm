package com.example.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm.R
import com.example.mvvm.viewmodel.MainViewModel

class MainView : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, View1.newInstance(),"View1")
                .commit()
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelTask()
    }
}