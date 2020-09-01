package com.example.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.viewmodel.MainViewModel

class Fragment2 : Fragment() {

    lateinit var nameKey    : TextView
    lateinit var nameValue  : EditText
    lateinit var ageKey     : TextView
    lateinit var ageValue   : TextView
    val viewModel           : MainViewModel by lazy { initViewModel() }

    fun initViewModel() : MainViewModel{
//        return ViewModelProvider(this).get(MainViewModel::class.java)
        return ViewModelProvider(activity!!).get(MainViewModel::class.java)
    }
    fun initView(v : View){
        nameKey     = v.findViewById(R.id.nameKey)
        nameValue   = v.findViewById(R.id.nameValue)
        ageKey      = v.findViewById(R.id.ageKey)
        ageValue    = v.findViewById(R.id.ageValue)
    }

    fun observerData(){
        viewModel.loadUser().observe(viewLifecycleOwner){user->
            nameValue.setText(user.name)
            ageValue.setText(user.age.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.view2, container, false)
        initView(v)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
//        viewModel.user.removeObserver(this)//无需
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}