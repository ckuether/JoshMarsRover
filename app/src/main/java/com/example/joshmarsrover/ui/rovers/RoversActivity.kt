package com.example.joshmarsrover.ui.rovers

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ActivityRoversBinding
import com.example.joshmarsrover.ui.common.FragmentNavigator
import com.example.joshmarsrover.ui.common.activity.BaseActivity
import com.example.joshmarsrover.ui.rovers.rovers_list.RoversFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoversActivity : BaseActivity() {

    @Inject lateinit var fragNavigator: FragmentNavigator

    private lateinit var binding: ActivityRoversBinding

    private val containerFrag: Fragment?
        get() = supportFragmentManager.findFragmentById(binding.fragContainer.id)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoversBinding.inflate(layoutInflater)
        requestedOrientation = if(isTablet) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(binding.root)

        binding.toolbarStandard.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if(isPhone && containerFrag == null) {
            attachRoversFragment()
        }
    }

    fun attachRoversFragment(){
        binding.toolbarStandard.toolbar.navigationIcon = null
        fragNavigator.navigateToRovers(binding.fragContainer.id)
    }

    fun attachRoverDetailsFragment(rover: Rover){
        if(isPhone) {
            binding.toolbarStandard.toolbar.setNavigationIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
        }
        fragNavigator.navigateToRoverDetails(binding.fragContainer.id, rover)
    }
    override fun onBackPressed() {
        if(isPhone && containerFrag !is RoversFragment){
            attachRoversFragment()
            return
        }
        super.onBackPressed()
    }
}