package com.example.premierleagueapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.premierleagueapp.core.domain.usecase.TeamUseCase

class HomeViewModel(teamUseCase: TeamUseCase) : ViewModel(){
    val team = teamUseCase.getAllTeam().asLiveData()
}