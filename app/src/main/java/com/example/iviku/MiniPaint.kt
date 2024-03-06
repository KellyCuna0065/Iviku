package com.example.iviku

/*import android.graphics.*

interface MiniPaint {
    var paint: Paint
    fun onButtonClick()
    fun onTouchEvent()
    fun clearCanvas()
}*/

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MiniPaint (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()
    private var startPoint: PointF? = null
    private var endPoint: PointF? = null
    private var isDrawing: Boolean = false
    var option: Int = 0

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isDrawing) {
            canvas.drawLine(startPoint!!.x, startPoint!!.y, endPoint!!.x, endPoint!!.y, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (option) {
            1 -> {
                paint.color = Color.BLUE
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        endPoint = PointF()
                        isDrawing = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        endPoint!!.x = event.x
                        endPoint!!.y = event.y
                        invalidate()
                    }
                    MotionEvent.ACTION_UP -> {

                    }
                    else -> {
                        isDrawing = false
                    }
                }

            }

            2 -> {

            }

            3 -> {

            }

            else -> {

            }
        }

        return true
    }

    fun clearCanvas() {
        paint.color = Color.WHITE
        invalidate()
    }

}