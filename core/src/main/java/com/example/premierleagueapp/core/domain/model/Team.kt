package com.example.premierleagueapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val idTeam: String,
    val strLeague: String,
    val strTeam: String,
    val strTeamShort: String,
    val strAlternate: String,
    val strKeywords: String,
    val strStadium: String,
    val strStadiumLocation: String,
    val strStadiumThumb: String,
    val strStadiumDescription: String,
    val intStadiumCapacity: String,
    val strDescriptionEN: String,
    val strTeamBadge: String,
    val intFormedYear: String,
    val strTeamFanart1: String,
    val strTeamFanart2: String,
    val strTeamFanart3: String,
    val strTeamFanart4: String,
    var isFavorite: Boolean
): Parcelable
