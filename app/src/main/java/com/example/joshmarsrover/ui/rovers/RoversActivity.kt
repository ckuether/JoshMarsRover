package com.example.joshmarsrover.ui.rovers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joshmarsrover.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoversActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}