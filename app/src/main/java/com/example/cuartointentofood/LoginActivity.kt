package com.example.cuartointentofood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cuartointentofood.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Authentication
        auth = Firebase.auth
        binding.signInAppCompatButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()
            when{
                mEmail.isEmpty() || mPassword.isEmpty() -> {
                    Toast.makeText(baseContext, "Correo o Contraseña incorrectos",
                        Toast.LENGTH_SHORT).show()
                }
                else ->{
                    SignIn(mEmail, mPassword)
                }
            }
        }
    }

    private fun SignIn (email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o Contraseña incorrectos",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload(){
        val intent = Intent (this, MainActivity::class.java)
        this.startActivity(intent)
    }
}