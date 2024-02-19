package com.example.pedrapapeltesoura

import android.app.Application
import com.example.pedrapapeltesoura.domain.repository.GameRepository
import com.example.pedrapapeltesoura.infrastructure.repository.GameRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}

val appModule = module {
    single<GameRepository> { GameRepositoryImpl() }
}

