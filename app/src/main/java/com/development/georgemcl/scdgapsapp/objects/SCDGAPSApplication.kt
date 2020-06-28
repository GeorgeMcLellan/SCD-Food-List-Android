package com.development.georgemcl.scdgapsapp.objects

import android.app.Application
import timber.log.Timber

class SCDGAPSApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.i("Application launched")

        Timber.plant(Timber.DebugTree())
        Timber.i("planted")
    }
}