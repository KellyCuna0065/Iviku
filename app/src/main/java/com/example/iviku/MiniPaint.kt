package com.example.iviku

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2

class MiniPaint (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()
    private var actions = mutableListOf<Int>()
    private var lines = mutableListOf<Pair<PointF, PointF>>()
    private var circles = mutableListOf<Pair<PointF, Float>>()
    private var arcs = mutableListOf<Triple<PointF, Float, Float>>()
    private var curves = mutableListOf<Path>()

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

        for (curve in curves) {
            canvas.drawPath(curve, paint)
        }

        for (arc in arcs) {
            canvas.drawArc(getRectF(arc.first, arc.second), 0f, arc.third, true, paint)
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
                        arcs.add(Triple(startPoint, 0f, 0f))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val lastIndex = arcs.lastIndex
                        val currentArc = arcs[lastIndex]
                        val radius = calculateRadius(currentArc.first, PointF(event.x, event.y))
                        val sweepAngle = calculateSweepAngle(currentArc.first, PointF(event.x, event.y))
                        arcs[lastIndex] = Triple(currentArc.first, radius, sweepAngle)
                        invalidate()
                    }
                }
                actions.add(2)
            }

            3 -> {
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
                actions.add(3)
            }

            4 -> {

            }

            5 -> {

            }

            6 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val startPoint = PointF(event.x, event.y)
                        val path = Path()
                        path.moveTo(startPoint.x, startPoint.y)
                        curves.add(path)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val lastIndex = curves.lastIndex
                        curves[lastIndex].lineTo(event.x, event.y)
                        invalidate()
                    }
                }
                actions.add(6)
            }

            else -> {

            }
        }

        return true
    }

    fun clearCanvas() {
        lines.clear()
        circles.clear()
        arcs.clear()
        curves.clear()
        invalidate()
    }

    private fun calculateRadius(startPoint: PointF, endPoint: PointF): Float {
        return PointF(startPoint.x - endPoint.x, startPoint.y - endPoint.y).length()
    }

    private fun calculateSweepAngle(startPoint: PointF, endPoint: PointF): Float {
        val angle = Math.toDegrees(atan2((endPoint.x - endPoint.y).toDouble(), (endPoint.x - startPoint.x).toDouble())).toFloat()
        return if (angle < 0) 360 + angle else angle
    }

    private fun getRectF(center: PointF, radius: Float): RectF {
        return RectF(center.x - radius, center.y - radius, center.x + radius, center.y + radius)
    }

}