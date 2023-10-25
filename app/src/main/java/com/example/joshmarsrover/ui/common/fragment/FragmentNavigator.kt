package com.example.joshmarsrover.ui.common.fragment

import androidx.fragment.app.FragmentManager
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.ui.rovers.rover_details.RoverDetailsFragment
import com.example.joshmarsrover.ui.rovers.rovers_list.RoversFragment
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FragmentNavigator @Inject constructor(private val fragmentManager: FragmentManager) {

//    fun navigateToRovers(containerId: Int){
//        val frag = RoversFragment.newInstance()
//        fragmentManager.beginTransaction().replace(containerId, frag).commit()
//    }

//    fun navigateToRoverDetails(containerId: Int, roverPos: Int){
//        val frag = RoverDetailsFragment.newInstance(roverPos)
//        fragmentManager.beginTransaction().replace(containerId, frag).commit()
//    }
}