package com.example.premierleagueapp.core.di

import androidx.room.Room
import com.example.premierleagueapp.core.data.TeamRepository
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
        val hostname= "www.thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/ljTDgm/k397r3IdZEKRul2NCPhqITZKGW8ue2nIVaGc=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/ Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
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
        TeamRepository(get(), get(), get())
    }
}