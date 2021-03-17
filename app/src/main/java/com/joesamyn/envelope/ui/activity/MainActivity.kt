package com.joesamyn.envelope.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joesamyn.envelope.R
import com.joesamyn.envelope.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* Private Variables*/
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind to Activity view
        binding = ActivityMainBinding.inflate(layoutInflater)

        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        setContentView(binding.root)

        // Set up navigation actions for bottom navigation view
        setupViews()
    }

    /**
     * Configures the bottom screen navigation tabs to navigate to the proper fragment destinations
     */
    private fun setupViews() {
        // Setup navigation controller with bottom navigation view
        binding.bottomNav.setupWithNavController(navController)

        // Setup action bar with nav controller (not sure if needed yet)
        setupActionBarWithNavController(navController)

    }
}