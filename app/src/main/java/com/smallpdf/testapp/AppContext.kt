package com.smallpdf.testapp

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.smallpdf.testapp.data.SharedPreferencesProvider
import com.smallpdf.testapp.helper.ImageLoader
import com.smallpdf.testapp.helper.Util
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppContext : Application() {
    var jacksonObjectMapper: ObjectMapper? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        jacksonObjectMapper = ObjectMapper()
        SharedPreferencesProvider.instance!!.init(this)
        Util.instance!!.init(this)
        ImageLoader.instance!!.init(this)
    }

    companion object {
        var instance: AppContext? = null
            private set
    }
}