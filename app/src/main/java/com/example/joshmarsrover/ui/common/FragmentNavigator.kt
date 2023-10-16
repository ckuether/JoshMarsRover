package com.example.joshmarsrover.ui.common

import androidx.fragment.app.FragmentManager
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.ui.rover_details.RoverDetailsFragment
import com.example.joshmarsrover.ui.rovers.RoversFragment
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FragmentNavigator @Inject constructor(private val fragmentManager: FragmentManager) {

    fun navigateToRovers(containerId: Int){
        val frag = RoversFragment.newInstance()
        fragmentManager.beginTransaction().replace(containerId, frag).commit()
    }

    fun navigateToRoverDetails(containerId: Int, rover: Rover){
        val frag = RoverDetailsFragment.newInstance(rover)
        fragmentManager.beginTransaction().replace(containerId, frag).commit()
    }
}