package com.example.premierleagueapp.core.domain.usecase

import com.example.premierleagueapp.core.data.Resource
import com.example.premierleagueapp.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamUseCase {
    fun getAllTeam(): Flow<Resource<List<Team>>>
    fun getAllFavoriteTeam(): Flow<List<Team>>
    fun setFavoriteTeam(team: Team, state: Boolean)
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}