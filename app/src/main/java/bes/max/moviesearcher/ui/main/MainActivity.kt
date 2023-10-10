package bes.max.moviesearcher.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import bes.max.moviesearcher.R
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.detailsFragment, R.id.castFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    fun animateBottomNavigationView() {
        //TODO after adding bottomNavigationView add:
        //binding.bottomNavigationView.visibility = View.GONE
    }


}