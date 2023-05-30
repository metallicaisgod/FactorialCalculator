package com.kirillmesh.factorialcalculator

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    private fun observeViewModel(){
        viewModel.error.observe(this){
            if(it){
                Toast.makeText(
                    this,
                    "There is not number in edit text",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.progress.observe(this){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
                binding.btCalculate.isEnabled = false
            } else {
                binding.progressBar.visibility = View.GONE
                binding.btCalculate.isEnabled = true
            }
        }
        viewModel.result.observe(this){
            binding.tvResult.text = it
        }
    }
}