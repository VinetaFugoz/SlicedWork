package com.slicedwork.slicedwork.presentation.activity

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.slicedwork.slicedwork.R
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

    override fun onStart() {
        super.onStart()
        colorStatusBar(false)
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

    fun colorStatusBar(isBlue: Boolean) {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isBlue) {
            window.decorView.systemUiVisibility = 0
            window.statusBarColor = ContextCompat.getColor(this, R.color.primaryDarkColor)
        }
        else if (!isBlue && resources.configuration.uiMode == 33) {
            window.decorView.systemUiVisibility = 0
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }
    }

    fun colorToolBar(color: Int) {
        binding.toolbar.background = ContextCompat.getDrawable(this, color)
    }

    fun hideToolbar() = this.supportActionBar?.hide()

    fun showToolbar() = this.supportActionBar?.show()

}