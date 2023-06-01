package br.com.rodrigoamora.examplekoin.app

import android.app.Application
import br.com.rodrigoamora.examplekoin.di.appModule
import br.com.rodrigoamora.examplekoin.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, dbModule)
        }
    }

}