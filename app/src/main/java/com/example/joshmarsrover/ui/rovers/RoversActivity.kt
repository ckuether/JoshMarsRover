package com.example.joshmarsrover.ui.rovers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.databinding.ActivityMainBinding
import com.example.joshmarsrover.ui.common.FragmentNavigator
import com.example.joshmarsrover.ui.rovers.rovers_list.RoversFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoversActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var fragmentNavigator: FragmentNavigator

    private val containerFrag: Fragment?
        get() = supportFragmentManager.findFragmentById(binding.container.id)

    lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoversViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToRovers()

        viewModel.navToRoverPosition.observe(this){
            it?.let{
                navigateToRoverDetails(it)
                viewModel.updateNavToRoverPos(null)
            }
        }
    }

    private fun navigateToRovers(){
        fragmentNavigator.navigateToRovers(binding.container.id)
        viewModel.updateNavToRoverPos(null)
    }

    fun navigateToRoverDetails(roverPos: Int){
        fragmentNavigator.navigateToRoverDetails(binding.container.id, roverPos)
    }

    override fun onBackPressed() {
        if(containerFrag !is RoversFragment){
            navigateToRovers()
            return
        }
        super.onBackPressed()
    }
}