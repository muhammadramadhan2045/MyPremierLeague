package com.example.premierleagueapp.core.domain.usecase

import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.core.domain.repository.ITeamRepository

class TeamInteractor(private val teamRepository: ITeamRepository): TeamUseCase {
    override fun getAllTeam()=teamRepository.getAllTeam()

    override fun getAllFavoriteTeam()=teamRepository.getAllFavoriteTeam()

    override fun setFavoriteTeam(team: Team, state: Boolean)=teamRepository.setFavoriteTeam(team, state)

}