package com.example.premierleagueapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.premierleagueapp.core.domain.usecase.TeamUseCase

class FavoriteViewModel(teamUseCase: TeamUseCase): ViewModel() {
    val favoriteTeam = teamUseCase.getAllFavoriteTeam().asLiveData()
}