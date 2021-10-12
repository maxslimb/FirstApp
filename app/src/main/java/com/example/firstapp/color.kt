package com.example.firstapp

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View


class color : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myview(this))
    }

    private inner class myview(context: Context?) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint()
            paint.textSize = 40f
            paint.color = Color.BLACK
            canvas.drawText("OVAL", 55f, 35f, paint)
            paint.color = Color.RED
            canvas.drawOval(140f, 90f, 70f, 270f, paint)
            paint.color = Color.BLACK
            canvas.drawText("RECTANGLE", 255f, 35f, paint)
            paint.color = Color.MAGENTA
            canvas.drawRect(270f, 60f, 400f, 340f, paint)
            paint.color = Color.BLACK
            canvas.drawText("SQUARE", 55f, 430f, paint)
            paint.color = Color.BLUE
            canvas.drawRect(50f, 450f, 150f, 550f, paint)
            paint.color = Color.BLACK
            canvas.drawText("LINE", 255f, 430f, paint)
            paint.color = Color.BLACK
            canvas.drawLine(250f, 500f, 350f, 500f, paint)
        }
    }
}
