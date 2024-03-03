package com.example.premierleagueapp.core.domain.usecase

import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.core.domain.repository.ITeamRepository
import kotlinx.coroutines.flow.Flow

class TeamInteractor(private val teamRepository: ITeamRepository): TeamUseCase {
    override fun getAllTeam()=teamRepository.getAllTeam()

    override fun getAllFavoriteTeam()=teamRepository.getAllFavoriteTeam()

    override fun setFavoriteTeam(team: Team, state: Boolean)=teamRepository.setFavoriteTeam(team, state)
    override fun getThemeSetting(): Flow<Boolean> {
        return teamRepository.getThemeSetting()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        teamRepository.saveThemeSetting(isDarkModeActive)
    }


}