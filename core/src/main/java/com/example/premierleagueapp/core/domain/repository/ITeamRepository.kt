package com.example.premierleagueapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.premierleagueapp.core.data.Resource
import com.example.premierleagueapp.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface ITeamRepository {

    fun getAllTeam(): Flow<Resource<List<Team>>>

    fun getAllFavoriteTeam(): Flow<List<Team>>

    fun setFavoriteTeam(team: Team, state: Boolean)
}