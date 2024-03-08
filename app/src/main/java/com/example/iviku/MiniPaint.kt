package com.example.iviku

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MiniPaint (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()
    private var actions = mutableListOf<Int>()
    private var lines = mutableListOf<Pair<PointF, PointF>>()
    private var circles = mutableListOf<Pair<PointF, Float>>()

    var option: Int = 0

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (line in lines) {
            canvas.drawLine(line.first.x, line.first.y, line.second.x, line.second.y, paint)
        }

        for (circle in circles) {
            canvas.drawCircle(circle.first.x, circle.first.y, circle.second, paint)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (option) {
            1 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val startPoint = PointF(event.x, event.y)
                        lines.add(Pair(startPoint, PointF()))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val lastIndex = lines.lastIndex
                        lines[lastIndex] = Pair(lines[lastIndex].first, PointF(event.x, event.y))
                        invalidate()
                    }
                }
                actions.add(1)
            }

            2 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val startPoint = PointF(event.x, event.y)
                        circles.add(Pair(startPoint, 0f))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val lastIndex = circles.lastIndex
                        val currentCircle = circles[lastIndex]
                        val radius = calculateRadius(currentCircle.first, PointF(event.x, event.y))
                        circles[lastIndex] = Pair(currentCircle.first, radius)
                        invalidate()
                    }
                }
                actions.add(2)
            }

            3 -> {

            }

            else -> {

            }
        }

        return true
    }

    fun clearCanvas() {
        lines.clear()
        circles.clear()
        invalidate()
    }

    private fun calculateRadius(startPoint: PointF, endPoint: PointF): Float {
        return PointF(startPoint.x - endPoint.x, startPoint.y - endPoint.y).length()
    }

}