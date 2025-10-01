package com.cirogg.joyrnm.di

import app.cash.sqldelight.db.SqlDriver
import com.cirogg.joyrnm.core.network.HttpClientFactory
import com.cirogg.joyrnm.data.local.CharacterLocalDataSource
import com.cirogg.joyrnm.data.local.DatabaseDriverFactory
import com.cirogg.joyrnm.data.remote.RickAndMortyApi
import com.cirogg.joyrnm.data.remote.RickAndMortyApiImpl
import com.cirogg.joyrnm.data.repository.CharacterRepositoryImpl
import com.cirogg.joyrnm.domain.repository.CharacterRepository
import com.cirogg.joyrnm.domain.usecase.GetCharactersPageUseCase
import com.cirogg.joyrnm.presentation.list.CharacterListViewModel
import com.cirogg.rnm.db.AppDatabase
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// --- NETWORK ---
val networkModule = module {
    single { HttpClientFactory.create() }
    single<RickAndMortyApi> { RickAndMortyApiImpl(get()) }
}

// --- DATABASE ---
val databaseModule = module {
    single { AppDatabase(get()) }
    single { get<AppDatabase>().characterQueries }
    single { CharacterLocalDataSource(get()) }
}

// --- DATA ---
val dataModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
}

// --- DOMAIN ---
val domainModule = module {
    factoryOf(::GetCharactersPageUseCase)
}

// --- PRESENTATION ---
val presentationModule = module {
    viewModel { CharacterListViewModel(get()) }
}

// --- APP MODULES ---
val appModules = listOf(
    networkModule,
    databaseModule, // DB primero porque depende de esto
    dataModule,
    domainModule,
    presentationModule
)