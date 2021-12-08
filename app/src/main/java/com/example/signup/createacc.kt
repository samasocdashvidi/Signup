package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class createacc : AppCompatActivity() {
    private lateinit var textemail : EditText
    private lateinit var createpass : EditText
    private lateinit var createpass1 : EditText
    private lateinit var signUp : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createacc)

        init()
        registerListener()
    }
    private fun init(){
        textemail = findViewById(R.id.textemail)
        createpass = findViewById(R.id.createpass)
        createpass1 = findViewById(R.id.createpass1)
        signUp = findViewById(R.id.signUp)
    }

    private fun registerListener(){
        signUp.setOnClickListener{
            val email1 = textemail.text.toString()
            val passwordd = createpass.text.toString()
            val password1 = createpass1.text.toString()

            if(textemail.text.isEmpty() || createpass.text.isEmpty() || createpass1.text.isEmpty()){
                Toast.makeText(this, "Please enter an email and password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(createpass.length()<9 || createpass1.length()<9){
                Toast.makeText(this, "Password should be minimum 9 characters", Toast.LENGTH_LONG).show()
            }
            if(passwordd != password1 ){
                Toast.makeText(this, "Passwords should be same", Toast.LENGTH_LONG).show()
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email1,passwordd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}