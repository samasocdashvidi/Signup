package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class resetpass : AppCompatActivity() {
    private lateinit var sendcode : Button
    private lateinit var resetemail : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)
        sendcode = findViewById(R.id.sendcode)
        resetemail = findViewById(R.id.resetemail)

        resgisterListener()
    }
    private fun resgisterListener(){
        sendcode.setOnClickListener {
            val resetemail1 = resetemail.text.toString()
            if(resetemail.text.isEmpty()){
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(resetemail1)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Check email to reset password", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}