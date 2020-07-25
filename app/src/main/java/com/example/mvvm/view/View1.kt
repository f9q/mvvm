package com.example.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.mvvm.R
import com.example.mvvm.viewmodel.MainViewModel

class View1 : Fragment() {

    lateinit var nameKey    : TextView
    lateinit var nameValue  : EditText
    lateinit var ageKey     : TextView
    lateinit var ageValue   : TextView
    val viewModel           : MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = View1()
    }

    fun initView(v : View){
        nameKey     = v.findViewById(R.id.nameKey)
        nameValue   = v.findViewById(R.id.nameValue)
        ageKey      = v.findViewById(R.id.ageKey)
        ageValue    = v.findViewById(R.id.ageValue)

        v.findViewById<Button>(R.id.btn_test).setOnClickListener(this::onTestClicked)
        v.findViewById<Button>(R.id.btn_view2).setOnClickListener(this::onView2Clicked)
    }

    fun onTestClicked(v: View){
        viewModel.cancelTask()
        viewModel.test2()
    }

    fun onView2Clicked(v: View){
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.addToBackStack("View2")?.add(R.id.container,View2(),"View2")?.commit()
    }

    fun bindViewModel(){
        viewModel.loadUser().observe(viewLifecycleOwner){user->
            nameValue.setText(user.name)
            ageValue.setText(user.age.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.view1, container, false)
        initView(v)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val vmp = ViewModelProvider(this)
//        viewModel = vmp.get(MainViewModel::class.java)
        bindViewModel()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
//        viewModel.user.removeObserver(this)//无需
    }

    override fun onDetach() {
        super.onDetach()
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