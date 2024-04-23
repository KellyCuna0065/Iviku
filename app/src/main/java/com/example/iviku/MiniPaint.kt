package com.example.iviku

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.min

class MiniPaint (context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint = Paint()
    private var lines = mutableListOf<Pair<PointF, PointF>>()
    private var circles = mutableListOf<Pair<PointF, Float>>()
    private var arcs = mutableListOf<Triple<PointF, Float, Float>>()
    private var curves = mutableListOf<Path>()
    private var ovals = mutableListOf<RectF>()
    private var rectangles = mutableListOf<RectF>()

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
            canvas.drawArc(getRectF(arc.first, arc.second), arc.third, 90f, false, paint)
        }

        for (oval in ovals) {
            canvas.drawOval(oval, paint)
        }

        for (rect in rectangles) {
            canvas.drawRect(rect, paint)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val startPoint: PointF
        val lastIndex: Int
        val radius: Float

        when (option) {
            1 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        lines.add(Pair(startPoint, PointF()))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = lines.lastIndex
                        lines[lastIndex] = Pair(lines[lastIndex].first, PointF(event.x, event.y))
                        invalidate()
                    }
                }
            }

            2 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        arcs.add(Triple(startPoint, 0f, 0f))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = arcs.lastIndex
                        val currentArc = arcs[lastIndex]
                        radius = calculateRadius(currentArc.first, PointF(event.x, event.y))
                        val startAngle = calculateAngle(currentArc.first, PointF(event.x, event.y))
                        arcs[lastIndex] = Triple(currentArc.first, radius, startAngle)
                        invalidate()
                    }
                }
            }

            3 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        circles.add(Pair(startPoint, 0f))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = circles.lastIndex
                        val currentCircle = circles[lastIndex]
                        radius = calculateRadius(currentCircle.first, PointF(event.x, event.y))
                        circles[lastIndex] = Pair(currentCircle.first, radius)
                        invalidate()
                    }
                }
            }

            4 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        rectangles.add(RectF(startPoint.x, startPoint.y, startPoint.x, startPoint.y))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = rectangles.lastIndex
                        val currentRect = rectangles[lastIndex]
                        val left = min(event.x, currentRect.left)
                        val top = min(event.y, currentRect.top)
                        val right = max(event.x, currentRect.right)
                        val bottom = max(event.y, currentRect.bottom)
                        rectangles[lastIndex] = RectF(left, top, right, bottom)
                        invalidate()
                    }
                }
            }

            5 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        ovals.add(RectF(startPoint.x, startPoint.y, startPoint.x, startPoint.y))
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = ovals.lastIndex
                        val currentOval = ovals[lastIndex]
                        val left = min(event.x, currentOval.left)
                        val top = min(event.y, currentOval.top)
                        val right = max(event.x, currentOval.right)
                        val bottom = max(event.y, currentOval.bottom)
                        ovals[lastIndex] = RectF(left, top, right, bottom)
                        invalidate()
                    }
                }
            }

            6 -> {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startPoint = PointF(event.x, event.y)
                        val path = Path()
                        path.moveTo(startPoint.x, startPoint.y)
                        curves.add(path)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastIndex = curves.lastIndex
                        curves[lastIndex].lineTo(event.x, event.y)
                        invalidate()
                    }
                }
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
        rectangles.clear()
        ovals.clear()
        invalidate()
    }

    private fun calculateRadius(startPoint: PointF, endPoint: PointF): Float {
        return PointF(startPoint.x - endPoint.x, startPoint.y - endPoint.y).length()
    }

    private fun calculateAngle(startPoint: PointF, endPoint: PointF): Float {
        val dx = endPoint.x - startPoint.x
        val dy = endPoint.y - startPoint.y
        val angle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
        return angle
    }

    private fun calculateSweepAngle(startPoint: PointF, endPoint: PointF): Float {
        val dx = endPoint.x - startPoint.x
        val dy = endPoint.y - startPoint.y
        val angle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
        return if (angle < 0) angle + 360f else angle
    }

    private fun getRectF(center: PointF, radius: Float): RectF {
        return RectF(center.x - radius, center.y - radius, center.x + radius, center.y + radius)
    }

}