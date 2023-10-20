package com.example.joshmarsrover.ui.rovers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.joshmarsrover.R
import com.example.joshmarsrover.databinding.ActivityMainBinding
import com.example.joshmarsrover.ui.rovers.rover_details.RoverDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoversActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment

    lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoversViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        viewModel.navToRoverPosition.observe(this){
            it?.let{
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_roversFragment_to_roversDetailsFragment, RoverDetailsFragment.setBundleArgs(it))
                viewModel.updateNavToRoverPos(null)
            }
        }
    }
}