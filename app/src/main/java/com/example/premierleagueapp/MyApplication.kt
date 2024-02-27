package com.example.premierleagueapp

import android.app.Application
import com.example.premierleagueapp.core.di.databaseModule
import com.example.premierleagueapp.core.di.networkModule
import com.example.premierleagueapp.core.di.repositoryModule
import com.example.premierleagueapp.di.prefModule
import com.example.premierleagueapp.di.useCaseModule
import com.example.premierleagueapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    prefModule
                )
            )
        }
    }
}