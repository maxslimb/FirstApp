package com.example.firstapp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView

class NumberListAdapter (var data: List<data_r2>): RecyclerView.Adapter<NumberListAdapter.IntViewHolder>() {
    class IntViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textView = row.findViewById<TextView>(R.id.colorname)
        val imageview = row.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.itemview1,parent,false)
        val holder =  IntViewHolder(layout)
       layout.setOnClickListener {
           val intent = Intent(parent.context,PaymentActivity::class.java)
           parent.context.startActivity(intent)
       }
        return holder
    }

    override fun onBindViewHolder(holder: IntViewHolder, position: Int) {
        val item = data[position]
        holder.imageview.background = item.color_hex
        holder.textView.text = item.color_name.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

// List: {1,2,3,4}

