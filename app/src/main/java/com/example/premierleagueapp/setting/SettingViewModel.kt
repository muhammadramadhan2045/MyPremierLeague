package com.example.premierleagueapp.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences): ViewModel() {
    fun getThemeSetting() = pref.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkModActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModActive)
        }
    }
}