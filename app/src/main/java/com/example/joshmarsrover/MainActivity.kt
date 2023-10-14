package com.example.joshmarsrover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.databinding.ActivityMainBinding
import com.example.joshmarsrover.ui.rovers.RoversFragment
import com.example.joshmarsrover.ui.rovers.RoversViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoversViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToRoverList()
    }

    private fun navigateToRoverList(){
        val frag = RoversFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.container.id, frag).commit()
    }
}