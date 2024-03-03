package com.example.premierleagueapp.core.di

import androidx.room.Room
import com.example.premierleagueapp.core.BuildConfig
import com.example.premierleagueapp.core.data.SettingPreferences
import com.example.premierleagueapp.core.data.TeamRepository
import com.example.premierleagueapp.core.data.dataStore
import com.example.premierleagueapp.core.data.source.local.LocalDataSource
import com.example.premierleagueapp.core.data.source.local.room.TeamDatabase
import com.example.premierleagueapp.core.data.source.remote.RemoteDataSource
import com.example.premierleagueapp.core.data.source.remote.network.ApiService
import com.example.premierleagueapp.core.domain.repository.ITeamRepository
import com.example.premierleagueapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule= module {
    factory {
        get<TeamDatabase>().teamDao()
    }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("premierleagueapp".toCharArray())
        val factory= SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TeamDatabase::class.java, "Team.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}

val networkModule = module {
    single {
        val hostname= BuildConfig.BASE_URL
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, BuildConfig.PIN1)
            .add(hostname, BuildConfig.PIN2)
            .add(hostname, BuildConfig.PIN3)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

}

val prefModule = module {
    factory {
        SettingPreferences(androidContext().applicationContext.dataStore)
    }
}



val repositoryModule = module{
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    single {
        AppExecutors()
    }
    single<ITeamRepository> {
        TeamRepository(get(), get(), get(),get())
    }
}