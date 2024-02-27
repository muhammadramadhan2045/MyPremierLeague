package com.example.premierleagueapp.detail

import androidx.lifecycle.ViewModel
import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.core.domain.usecase.TeamUseCase

class   DetailViewModel(private val teamUseCase: TeamUseCase) :ViewModel() {
    fun setFavoriteTeam(team: Team, newStatus:Boolean) = teamUseCase.setFavoriteTeam(team, newStatus)
}