package com.example.gymapptest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gymapptest.R
import com.example.gymapptest.databinding.ActivityMainBinding
import com.example.gymapptest.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel:MainViewModel by viewModels()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.preview)

            val state = mainViewModel.allmodel// ?: NetworkResult.Loading()
            state.observe(this,Observer{
                NetworkResult.Success(data = it)
                setContentView(binding.root)
                val navController = findNavController(R.id.nav_host)
                val bottomnav = binding.bottomNavMenu
                bottomnav.setupWithNavController(navController)


            })





    }
}