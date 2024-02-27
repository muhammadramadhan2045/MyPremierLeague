package com.example.premierleagueapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.premierleagueapp.core.data.source.local.LocalDataSource
import com.example.premierleagueapp.core.data.source.remote.RemoteDataSource
import com.example.premierleagueapp.core.data.source.remote.network.ApiResponse
import com.example.premierleagueapp.core.data.source.remote.response.TeamResponse
import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.core.domain.repository.ITeamRepository
import com.example.premierleagueapp.core.utils.AppExecutors
import com.example.premierleagueapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.example.premierleagueapp.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : ITeamRepository {

    override fun getAllTeam(): Flow<com.example.premierleagueapp.core.data.Resource<List<Team>>> =
        object : com.example.premierleagueapp.core.data.NetworkBoundResource<List<Team>, List<TeamResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Team>> {
                return localDataSource.getAllTeam().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TeamResponse>>> =
                remoteDataSource.getAllTeam()

            override suspend fun saveCallResult(data: List<TeamResponse>) {
                val teamList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTeam(teamList)
            }

            override fun shouldFetch(data: List<Team>?): Boolean =
                data == null || data.isEmpty()

        }.asFlow()


    override fun getAllFavoriteTeam():Flow<List<Team>> {
        return localDataSource.getFavoriteTeam().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapDomainToEntity(team)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTeam(teamEntity, state) }
    }


}