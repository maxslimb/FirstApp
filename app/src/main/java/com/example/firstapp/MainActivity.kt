package com.example.firstapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    val TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(applicationContext)

        val response_textview = findViewById<TextView>(R.id.text1)
        val button = findViewById<Button>(R.id.bt1)
        button.setOnClickListener {

            AndroidNetworking.get("https://doubtconnect.in:3001")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {

                        Log.d(TAG,"response from server : $response")
                        response_textview.text = response
                    }

                    override fun onError(anError: ANError?) {
                        Log.e(TAG,"error from server : ${anError.toString()}")
                    }

                })
        }


        val data1 = arrayListOf<data_r2>()
      // data1.add(data_r2("https://firebasestorage.googleapis.com/v0/b/doubtconnect-a1cf3.appspot.com/o/doubts%2F20200503_024701.jpg?alt=media&token=fa18c9ca-286f-4a1a-a917-6e738abf19bc","Grey"))
        data1.add(data_r2(R.color.purple_200,"RED"))
        data1.add(data_r2(R.color.teal_200,"GREEN"))



        val recycler = findViewById<RecyclerView>(R.id.recycler2)
        recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        recycler.adapter = NumberListAdapter(data1)

    }
}