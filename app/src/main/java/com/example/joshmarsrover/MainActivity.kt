package com.example.joshmarsrover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ActivityMainBinding
import com.example.joshmarsrover.ui.rover_details.RoverDetailsFragment
import com.example.joshmarsrover.ui.rovers.RoversFragment
import com.example.joshmarsrover.ui.rovers.RoversViewModel
import dagger.hilt.android.AndroidEntryPoint

interface RoversCallback {
    var viewModel: RoversViewModel
    fun navigateToRovers()
    fun navigateToRoverDetails(rover: Rover)
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RoversCallback {

    private lateinit var binding: ActivityMainBinding

    private val containerFrag: Fragment?
        get() = supportFragmentManager.findFragmentById(binding.container.id)

    override lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoversViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToRovers()
    }

    override fun navigateToRovers(){
        val frag = RoversFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.container.id, frag).commit()
    }

    override fun navigateToRoverDetails(rover: Rover){
        val frag = RoverDetailsFragment.newInstance(rover)
        supportFragmentManager.beginTransaction().replace(binding.container.id, frag).commit()
    }

    override fun onBackPressed() {
        if(containerFrag !is RoversFragment){
            navigateToRovers()
            return
        }
        super.onBackPressed()
    }
}