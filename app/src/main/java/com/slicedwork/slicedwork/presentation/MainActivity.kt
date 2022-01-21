package com.slicedwork.slicedwork.presentation

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.ActivityMainBinding
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        setTheme(R.style.Theme_SlicedWork)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        navHostFragment = getNavHostFragment()
        navController = getNavController()

        setupDrawerLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

    private fun getNavController(): NavController = navHostFragment.navController

    private fun setupDrawerLayout() {
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }
}