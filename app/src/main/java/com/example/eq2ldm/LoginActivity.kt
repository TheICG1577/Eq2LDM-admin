package com.example.eq2ldm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eq2ldm.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MyApp).setAppLanguage("es")

        firebaseauth = FirebaseAuth.getInstance()
        binding.crear.setOnClickListener{
            val intent = Intent(this , SignInActivity::class.java)
            startActivity(intent)
        }

        binding.entrada.setOnClickListener {
            val usuario = binding.emailt.text.toString()
            val contra = binding.passwort.text.toString()

            if (usuario.isNotEmpty() && contra.isNotEmpty()) {
                firebaseauth.signInWithEmailAndPassword(usuario, contra).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = FirebaseAuth.getInstance().currentUser
                        if (currentUser != null && currentUser.isEmailVerified) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            val builder = AlertDialog.Builder(this)
                            builder.setTitle(getString(R.string.error))
                                .setMessage(getString(R.string.error_correo))
                                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                                }
                                .show()
                        }
                    } else {
                        val errorMessage = getString(R.string.Datos_inc)
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(getString(R.string.error))
                            .setMessage(errorMessage)
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                            }
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.Oblig), Toast.LENGTH_SHORT).show()
            }
        }


    }
}