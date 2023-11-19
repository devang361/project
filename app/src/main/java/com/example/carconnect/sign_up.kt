package com.example.carconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.carconnect.databinding.ActivitySignUpBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent;


class sign_up : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signin.setOnClickListener {
            val intent = Intent(this,sign_in::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val phone = binding.phone.text.toString()
            val name = binding.name.text.toString()

            if (email.isNotBlank() && pass.isNotEmpty() && phone.isNotBlank() && name.isNotEmpty()){


                firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        // Successful sign-up
                        val intent = Intent(this, sign_in::class.java)
                        startActivity(intent)
                    } else {
                        // Error during sign-up
                        val exception = task.exception
                        Toast.makeText(this, "Failed: " + exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }


            }else{
                Toast.makeText(this, "Enter all Fields" , Toast.LENGTH_SHORT).show()
            }

        }
    }
}