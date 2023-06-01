package br.com.rodrigoamora.examplekoin.di

import androidx.room.Room
import br.com.rodrigoamora.examplekoin.database.AppDatabase
import br.com.rodrigoamora.examplekoin.repository.ContactRepository
import br.com.rodrigoamora.examplekoin.repository.impl.ContactRepositoryImpl
import br.com.rodrigoamora.examplekoin.ui.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            repositoryModule,
            viewModelModule)
    )
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "example-koin-database")
            .build()
    }

    single { get<AppDatabase>().contactDao() }
}

val repositoryModule = module {
    single<ContactRepository> { ContactRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { ContactViewModel(get()) }
}
