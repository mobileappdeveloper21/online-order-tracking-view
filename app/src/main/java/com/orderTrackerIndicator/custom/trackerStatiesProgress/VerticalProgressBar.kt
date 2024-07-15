package com.orderTrackerIndicator.custom.trackerStatiesProgress

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ProgressBar

class VerticalProgressBar : ProgressBar {

    private var x = 0
    private var y = 0
    private var z = 0
    private var w = 0

    private var isOnTouchEnable =false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun drawableStateChanged() {
        super.drawableStateChanged()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
        this.x = w
        this.y = h
        this.z = oldw
        this.w = oldh
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(c: Canvas) {
        c.rotate(-90f)
        c.translate((-height).toFloat(), 0f)
        super.onDraw(c)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isOnTouchEnable) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isSelected = true
                isPressed = true
            }
            MotionEvent.ACTION_MOVE -> {
                progress = max - (max * event.y / height).toInt()
                onSizeChanged(width, height, 0, 0)
            }
            MotionEvent.ACTION_UP -> {
                isSelected = false
                isPressed = false
            }
            MotionEvent.ACTION_CANCEL -> {
                // Handle cancel
            }
        }
        return true
    }

    override fun setProgress(progress: Int) {
        if (progress >= 0) {
            super.setProgress(progress)
        } else {
            super.setProgress(0)
        }
        onSizeChanged(x, y, z, w)
    }
}