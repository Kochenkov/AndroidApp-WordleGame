package com.vkochenkov.wordlegame

import android.app.Application
import com.vkochenkov.wordlegame.di.appModule
import com.vkochenkov.wordlegame.di.dataModule
import com.vkochenkov.wordlegame.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}