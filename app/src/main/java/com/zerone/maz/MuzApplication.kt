package com.zerone.maz

import android.app.Application
import com.zerone.maz.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MuzApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MuzApplication)
            modules(koinModule)
        }
    }

}