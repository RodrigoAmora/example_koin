package br.com.rodrigoamora.examplekoin.di

import androidx.room.Room
import br.com.rodrigoamora.examplekoin.database.AppDatabase
import br.com.rodrigoamora.examplekoin.repository.ContactRepository
import br.com.rodrigoamora.examplekoin.repository.impl.ContactRepositoryImpl
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "example-koin-database")
        .build()
    }

    single { get<AppDatabase>().contactDao() }
}

val appModule = module {
    single<ContactRepository> { ContactRepositoryImpl() }
    viewModel { ContactViewModel(get()) }
}