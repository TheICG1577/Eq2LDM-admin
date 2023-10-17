package com.example.eq2ldm

import android.app.Application
import android.content.res.Configuration
import java.util.Locale

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setAppLanguage("es")
    }



    fun setAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
