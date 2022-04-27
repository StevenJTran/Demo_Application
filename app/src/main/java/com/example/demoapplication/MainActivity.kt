package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.demoapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            userBtn.setOnClickListener {
                viewModel.onAction(MainViewModel.Action.SaveUserInput(userEt.text.toString()))
            }

        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.userInputUpdated.observe(this) { userInputUpdated ->
            if (userInputUpdated) {
                with(binding) {
                    userTv.text = viewModel.getSavedUserInput()
                }
            }
        }
    }
}