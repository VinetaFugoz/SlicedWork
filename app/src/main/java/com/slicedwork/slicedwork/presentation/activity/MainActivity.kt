package com.slicedwork.slicedwork.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.slicedwork.slicedwork.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setProps()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.navigationView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setProps() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        navHostFragment = getNavHostFragment()
        navController = getNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getNavHostFragment(): NavHostFragment = supportFragmentManager
        .findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment

    private fun getNavController(): NavController = navHostFragment.navController

    fun colorStatusBar(color: Int) {
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

    fun colorToolBar(color: Int) {
        binding.toolbar.background = ContextCompat.getDrawable(this, color)
    }

    fun hideToolbar() = this.supportActionBar?.hide()

    fun showToolbar() = this.supportActionBar?.show()

}