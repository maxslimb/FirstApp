package com.example.firstapp.MainScreens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.userdata
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    val TAG = "Profile Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val name = findViewById<EditText>(R.id.name_profile)
        val email = findViewById<EditText>(R.id.email_profile)
        val profile_image =findViewById<ImageView>(R.id.imageView2_profile)
        val submit_button = findViewById<Button>(R.id.submit_bt_profile)

        val current_user = intent.getBooleanExtra("current-user",false)


        val database = Firebase.database.getReference("Users/${Firebase.auth.uid}")

        database.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name.setText(snapshot.child("name").value.toString())
                email.setText(snapshot.child("email").value.toString())
                if(snapshot.child("photoUrl").value.toString() != "")
                Glide.with(this@ProfileActivity)
                    .load(snapshot.child("photoUrl").value.toString())
                    .centerCrop()
                    .into(profile_image)
                Log.d(TAG,snapshot.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG,error.toString())
            }
        })

        submit_button.setOnClickListener {

            updateData(name.text.toString(),email.text.toString())
        }


    }

    private fun updateData(name: String, email: String) {
        val database = Firebase.database.reference

        val user_data = userdata(name,email)
        val data = user_data.toMap()
        val userupdates = hashMapOf<String, Any>("Users/${Firebase.auth.uid}/" to data)
        database.updateChildren(userupdates).addOnSuccessListener {
            Log.d(TAG,"Successfully stored user data to firebase db")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}