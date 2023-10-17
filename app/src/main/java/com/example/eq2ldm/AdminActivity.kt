package com.example.eq2ldm

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var toolsadmin: ImageButton
    private lateinit var usadmin: ImageButton
    private lateinit var back: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        (application as MyApp).setAppLanguage("es")

        back = findViewById(R.id.back)

        auth = FirebaseAuth.getInstance()

        firestore = FirebaseFirestore.getInstance()

        toolsadmin = findViewById(R.id.toolsadmin)

        usadmin = findViewById(R.id.usadmin)

        toolsadmin.setOnClickListener {
            Roleset("Admin")
             plus()}

        usadmin.setOnClickListener {
            Roleset("Usuario")
            user()}

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun Roleset(rol: String) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid

            val userData = hashMapOf(
                "rol" to rol
            )

            firestore.collection("users")
                .document(uid)
                .set(userData)
                .addOnSuccessListener {

                }
                .addOnFailureListener { e ->

                }
        }
    }



    fun plus() {
        val intent = Intent(this, AdminPlusActivity::class.java)
        startActivity(intent)
    }

    fun user() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
