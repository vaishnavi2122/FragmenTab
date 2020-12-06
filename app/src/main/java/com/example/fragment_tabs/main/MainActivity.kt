package com.example.fragment_tabs.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fragment_tabs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting navigation controller reference
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        //Enabling toolbar instead of action bar
        setSupportActionBar(toolbar)  

        //Sets Navigation for fragment with graph
        setUpViews()
    }

    //Navigation Architecture component for navigation between fragments
    private fun setUpViews() {
        //Setting navigation with Bottom tab bar
        bottom_navigation_view.setupWithNavController(navController)

        //Setting navigation with toolbar
        NavigationUI.setupWithNavController(toolbar, navController)

        //Setting back stack event on toolbar back pressed
        toolbar.setNavigationOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.search_fragment, R.id.notification_fragment, R.id.profile_fragment -> {
                    if (onBackPressedDispatcher.hasEnabledCallbacks())
                        onBackPressedDispatcher.onBackPressed()
                    else
                        navController.navigateUp()
                }
                else -> navController.navigateUp()
            }
        }
    }
}