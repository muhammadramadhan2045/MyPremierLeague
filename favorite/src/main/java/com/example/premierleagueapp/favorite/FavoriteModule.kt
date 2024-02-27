package com.example.premierleagueapp.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule= module{
    viewModel { FavoriteViewModel(get()) }
}