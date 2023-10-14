package com.example.joshmarsrover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joshmarsrover.databinding.ActivityMainBinding
import com.example.joshmarsrover.ui.rover_list.RoversFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToRoverList()
    }

    private fun navigateToRoverList(){
        val frag = RoversFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.container.id, frag).commit()
    }


}