package com.example.premierleagueapp.core.data.source.remote.network

import com.example.premierleagueapp.core.data.source.remote.response.ListTeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search_all_teams.php")
    suspend fun getListTeam(@Query("l") league: String): ListTeamResponse

}