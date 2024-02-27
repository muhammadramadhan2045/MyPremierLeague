package com.example.premierleagueapp.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.premierleagueapp.R
import com.example.premierleagueapp.databinding.ActivitySettingBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val pref: SettingPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarSetting.toolbar)
        supportActionBar?.title = getString(R.string.menu_setting)

        val switchTheme=binding.switchTheme

        val settingViewModel: SettingViewModel by viewModel()

        settingViewModel.getThemeSetting().observe(this){isDarkModeActive:Boolean ->
            if(isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked=true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked=false
            }
        }

        switchTheme.setOnCheckedChangeListener { compoundButton, isChecked ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }
}