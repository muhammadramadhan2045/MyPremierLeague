package com.example.premierleagueapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.premierleagueapp.core.data.source.local.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamDatabase  : RoomDatabase(){
    abstract fun teamDao(): TeamDao

}