package com.example.premierleagueapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.premierleagueapp.databinding.ActivityMainBinding
import com.example.premierleagueapp.setting.SettingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private  var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.appBarMain.toolbar)


        getThemeMode()



        val navView:BottomNavigationView = binding.navView

        val navController= findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration= AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_setting
            )
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

    }

        private fun getThemeMode() {
            viewModel.getThemeSetting().observe(this){isDarkModeActive:Boolean ->
                if(isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_favorite ->{
                startActivity(Intent(this,Class.forName("com.example.premierleagueapp.favorite.FavoriteActivity")))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.saveThemeSetting(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        _binding=null
    }

    override fun finishAfterTransition() {
        super.finishAfterTransition()
        finish()
    }

}