package com.example.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginactivity.MainActivity
import com.example.loginactivity.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val userEmail = binding.username.text.toString()
            val password = binding.password.text.toString()
            doLogin(userEmail, password)
        }


    }
    private fun doLogin(userEmail: String, password: String){
        Firebase.auth.signInWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    startActivity(
                        Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Log.w("LoginActivity", "signInWithEmail",it.exception)
                    Toast.makeText(this,"Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }
}