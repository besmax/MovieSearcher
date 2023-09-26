package bes.max.moviesearcher.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bes.max.moviesearcher.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun animateBottomNavigationView() {
        //TODO after adding bottomNavigationView add:
        //binding.bottomNavigationView.visibility = View.GONE
    }


}