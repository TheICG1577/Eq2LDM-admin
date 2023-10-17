package com.example.eq2ldm

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class LanguageActivity : AppCompatActivity() {

    private lateinit var langswitch: Switch
    private lateinit var back: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        (application as MyApp).setAppLanguage("es")

        back = findViewById(R.id.back)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        langswitch = findViewById(R.id.langswitch)

        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val currentLanguage = sharedPreferences.getString("language", "es")


        langswitch.isChecked = currentLanguage == "en"

        langswitch.setOnCheckedChangeListener { _, isChecked ->

            val selectedLanguage = if (isChecked) "en" else "es"
            sharedPreferences.edit().putString("language", selectedLanguage).apply()


            setAppLanguage(selectedLanguage)


            restartApp()
        }
    }

    private fun setAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}