package com.example.carconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carconnect.databinding.ActivitySignInBinding
import com.example.carconnect.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class sign_in : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signup.setOnClickListener {
            val intent = Intent(this,sign_up::class.java)
            startActivity(intent)
        }

        binding.button1.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()

            if (email.isNotBlank() && pass.isNotEmpty() ){

                firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener{
                    if(it.isSuccessful){


                        val intent = Intent(this,SplashActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "User Not Found, Please Sign UP" , Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Enter all Required Fields" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}