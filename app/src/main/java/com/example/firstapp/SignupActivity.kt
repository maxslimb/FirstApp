package com.example.firstapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.MainScreens.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SignupActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    val TAG = "Signup Activity"
     lateinit var profile_image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val database = Firebase.database.reference
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
         profile_image = findViewById<ImageView>(R.id.imageView2)

        val submit_button = findViewById<Button>(R.id.submit_bt)



        profile_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,123)
        }

        submit_button.setOnClickListener {
            val user_data = userdata(name.text.toString(),email.text.toString())
            val data = user_data.toMap()
            val userupdates = hashMapOf<String, Any>("Users/${Firebase.auth.uid}/" to data)
            database.updateChildren(userupdates).addOnSuccessListener {
                Log.d(TAG,"Successfully stored user data to firebase db")
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123 && resultCode== Activity.RESULT_OK && data !=null){
            val imagefile = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imagefile)
            val bitmapdrawable = BitmapDrawable(resources,bitmap)
            profile_image.setImageDrawable(bitmapdrawable)
            uploadimagetofirebase(imagefile)
        }
    }

    private fun uploadimagetofirebase(imagefile: Uri?) {
        val filename = "profile_image"
        val storage_ref = Firebase.storage.getReference("images/${Firebase.auth.uid}/$filename")
       val uploadTask =  storage_ref.putFile(imagefile!!).addOnSuccessListener {
            Log.d(TAG,"successfully uploaded the image")
           storage_ref.downloadUrl.addOnSuccessListener {
               write_photourl(it.toString())
           }
        }

    }

    private fun write_photourl(imageurl: String) {
        val database = Firebase.database.getReference("Users")
        database.child("${Firebase.auth.uid}").child("photoUrl").setValue(imageurl)
    }


}