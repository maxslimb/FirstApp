package com.example.firstapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val textview = findViewById<TextView>(R.id.text1)
//        val button = findViewById<Button>(R.id.bt1)
//        button.setOnClickListener {
//            textview.text = "Hi I am Kishan !"
//        }
//
        val a: Drawable
        val data1 = arrayListOf<data_r2>()
        data1.add(data_r2(a!!,"white"))

        val recycler = findViewById<RecyclerView>(R.id.recycler2)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = NumberListAdapter(data1)

    }
}