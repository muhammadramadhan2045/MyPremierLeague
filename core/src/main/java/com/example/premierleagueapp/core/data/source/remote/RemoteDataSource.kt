package com.example.premierleagueapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.premierleagueapp.core.data.source.remote.network.ApiResponse
import com.example.premierleagueapp.core.data.source.remote.network.ApiService
import com.example.premierleagueapp.core.data.source.remote.response.ListTeamResponse
import com.example.premierleagueapp.core.data.source.remote.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getAllTeam(): Flow<ApiResponse<List<TeamResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListTeam("English Premier League")
                val dataArray = response.teams
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



}