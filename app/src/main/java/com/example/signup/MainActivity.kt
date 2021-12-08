package com.example.signup
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var emailtext : EditText
    private lateinit var passwordtext : EditText
    private lateinit var login : Button
    private lateinit var createacc : Button
    private lateinit var forgotpassword : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailtext = findViewById(R.id.emailtext)
        passwordtext = findViewById(R.id.password)
        login = findViewById(R.id.login)
        createacc = findViewById(R.id.createacc)
        forgotpassword = findViewById(R.id.forgotpassword)

        forgotpassword.setOnClickListener {
            startActivity(Intent(this, resetpass::class.java))
            finish()
        }


        login.setOnClickListener{
            val email = emailtext.text.toString()
            val password = passwordtext.text.toString()


            if(emailtext.text.isEmpty() || passwordtext.text.isEmpty()){
                Toast.makeText(this, "Please enter an email and password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(passwordtext.length()<9){
                Toast.makeText(this, "Password should be minimum 9 characters", Toast.LENGTH_LONG).show()
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        goToProfile()
                    }else{
                        Toast.makeText(this, "Something is wrong, try again", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        createacc.setOnClickListener{
            val intent = Intent(this, com.example.signup.createacc::class.java)
            startActivity(intent)
        }
    }
    private fun goToProfile(){
        startActivity(Intent(this, loginsuccessfully::class.java))
        finish()
    }
}