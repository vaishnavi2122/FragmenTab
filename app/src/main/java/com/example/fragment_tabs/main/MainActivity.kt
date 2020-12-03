package com.example.fragment_tabs.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fragment_tabs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting navigation controller reference
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //Sets Navigation for fragment with graph
        setUpViews()
    }

    //Navigation Architecture component for navigation between fragments
    private fun setUpViews() {
        //Setting navigation with Bottom tab bar
        bottom_navigation_view.setupWithNavController(navController)

        //Setting navigation with action bar
        setupActionBarWithNavController(
            this, navController)
    }

    //For supporting up navigation
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}