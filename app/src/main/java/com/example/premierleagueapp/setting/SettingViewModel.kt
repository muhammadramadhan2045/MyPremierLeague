package com.example.premierleagueapp.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.core.domain.usecase.TeamUseCase
import kotlinx.coroutines.launch

class SettingViewModel(private val teamUseCase: TeamUseCase) : ViewModel() {
    fun getThemeSetting() = teamUseCase.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkModActive: Boolean){
        viewModelScope.launch {
            teamUseCase.saveThemeSetting(isDarkModActive)
        }
    }
}