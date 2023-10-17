package com.example.eq2ldm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AdminPlusActivity : AppCompatActivity() {

    private lateinit var back: ImageButton
    private lateinit var FB: ImageButton
    private lateinit var FS: ImageButton

    private  var UFB = "https://console.firebase.google.com/u/0/project/eq2ldm/authentication/users"
    private  var UFS = "https://console.firebase.google.com/u/0/project/eq2ldm/firestore/data/~2Fusers~2FgfUughpyp4UlADEzfsnOKFwTR3J3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_plus)

        (application as MyApp).setAppLanguage("es")

        FB = findViewById(R.id.FB)
        FS = findViewById(R.id.FS)

        back = findViewById(R.id.back)

        back.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }


        FB.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UFB))
            startActivity(intent)
        }

        FS.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UFS))
            startActivity(intent)
        }


    }


}
