package com.example.firstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    val TAG = "Signup Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
            val database = Firebase.database.reference
        val name = findViewById<EditText>(R.id.name)
         val email = findViewById<EditText>(R.id.email)

        val submit_button = findViewById<Button>(R.id.submit_bt)

        submit_button.setOnClickListener {
            val user_data = userdata(name.text.toString(),email.text.toString())
            val data = user_data.toMap()
            val userupdates = hashMapOf<String, Any>("Users/${Firebase.auth.uid}/" to data)
            database.updateChildren(userupdates).addOnSuccessListener {
                Log.d(TAG,"Successfully stored user data to firebase db")
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}