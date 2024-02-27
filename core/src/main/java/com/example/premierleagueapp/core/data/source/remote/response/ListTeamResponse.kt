package com.example.premierleagueapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTeamResponse(
    @field:SerializedName("teams")
    val teams: List<TeamResponse>
)
