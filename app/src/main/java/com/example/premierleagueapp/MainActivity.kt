package com.example.premierleagueapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.premierleagueapp.databinding.ActivityMainBinding
import com.example.premierleagueapp.setting.SettingActivity
import com.example.premierleagueapp.setting.SettingPreferences
import com.example.premierleagueapp.setting.SettingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val pref: SettingPreferences by inject()
    private val viewModel: SettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.appBarMain.toolbar)


        getThemeMode()




        val navView:BottomNavigationView = binding.navView

        val navController= findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration= AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_favorite
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

        val id=item.itemId
        when(id){
            R.id.action_change_settings ->{
                //intent to HomeActivity
                val intent= Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}