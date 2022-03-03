package com.example.demo.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.network.ApiHelper
import com.example.demo.network.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewDataBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MyViewModelFactory(ApiHelper(RetrofitClient.apiServices))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewDataBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewDataBinding.mainViewModel = mainViewModel
        mainViewDataBinding.lifecycleOwner = this

        setContentView(mainViewDataBinding.root)

        mainViewModel.mediatorLiveData.observe(this){
            mainViewDataBinding.count = it.toString()
        }
    }
}