package com.example.eq2ldm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eq2ldm.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AlertDialog


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MyApp).setAppLanguage("es")

        firebaseauth = FirebaseAuth.getInstance()

        binding.inic.setOnClickListener{
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }
        binding.reg.setOnClickListener{
            val usuario = binding.emailt.text.toString()
            val contra = binding.passwort.text.toString()
            val vcontra = binding.confirmaPasst.text.toString()

            if (usuario.isNotEmpty() && contra.isNotEmpty() && vcontra.isNotEmpty()){
                if (contra == vcontra){

                    firebaseauth.createUserWithEmailAndPassword(usuario, contra).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            firebaseauth.currentUser?.sendEmailVerification()
                                ?.addOnCompleteListener { verif ->
                                    if (verif.isSuccessful) {
                                        val builder = AlertDialog.Builder(this)
                                        builder.setTitle(getString(R.string.error))
                                            .setMessage(getString(R.string.error_email))
                                            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                                                val intent = Intent(this, LoginActivity::class.java)
                                                startActivity(intent)
                                            }
                                            .show()
                                    } else {
                                        val errorMessage = getString(R.string.error_verif)
                                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this , getString(R.string.Datos_inc) , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , getString(R.string.Oblig), Toast.LENGTH_SHORT).show()
            }
        }
    }
}