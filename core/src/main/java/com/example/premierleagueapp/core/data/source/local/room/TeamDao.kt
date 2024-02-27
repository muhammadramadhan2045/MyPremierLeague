package com.example.premierleagueapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.premierleagueapp.core.data.source.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM team")
    fun getAllTeam() : Flow<List<TeamEntity>>

    @Query("SELECT * FROM team where isFavorite = 1")
    fun getFavoriteTeam() : Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: List<TeamEntity>)

    @Update
    fun updateFavoriteTeam(team: TeamEntity)

}