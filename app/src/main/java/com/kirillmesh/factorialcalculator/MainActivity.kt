package com.kirillmesh.factorialcalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kirillmesh.factorialcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.btCalculate.setOnClickListener {
            viewModel.calculate(binding.etNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.btCalculate.isEnabled = true
            when(it){
                is Error -> {
                    Toast.makeText(
                        this,
                        "There is not number in edit text",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btCalculate.isEnabled = false
                }
                is Result -> {
                    binding.tvResult.text = it.result
                }
            }
        }
    }
}