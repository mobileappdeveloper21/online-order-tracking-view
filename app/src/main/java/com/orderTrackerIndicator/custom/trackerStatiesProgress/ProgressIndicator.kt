package com.orderTrackerIndicator.custom.trackerStatiesProgress

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.orderTrackerIndicator.R
import com.orderTrackerIndicator.util.toBitmap


data class Indicator(
    val completedIcon:Bitmap? = null,
    val workingIcon:Bitmap?= null,
    val iconSize:Float = 0.0f,
    val lineWidth:Float = 0.0f,
    val showImageSize:Float = 30f
)


class ProgressIndicator @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr){


    private var PROGRESS_COUNT = 7

    /**
     * width of one cell = stageWidth/noOfStates
     */
    private var mCellWidth = 0f

    /**
     * next cell(state) from previous cell
     */
    private var mNextCellWidth = 0f

    /**
     * is View Horizontal and Vertical
     * */
    private var isHorizontal:Boolean = false


    private var mStateRadius = 0f


    init {
        mStateRadius = PROGRESS_COUNT / 2f
    }

    private var trackerStateList:ArrayList<Indicator> = arrayListOf()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    public fun setArray(trackerStateList:List<Indicator>){
        this.trackerStateList.clear()
        this.trackerStateList.addAll(trackerStateList)
    }


    private val lineBackgroundPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCellWidth = (width / PROGRESS_COUNT).toFloat()
        mNextCellWidth = mCellWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawStatusProgress(canvas)
    }

    private fun drawStatusProgress(canvas: Canvas) {
        createLineIndicator(canvas)
        createCircle(canvas)
    }

    private fun createCircle(canvas: Canvas) {
        drawCircles(canvas)
    }

    private fun drawCircles(canvas: Canvas) {

        for(i in 0 until PROGRESS_COUNT){
            val x = mCellWidth * (i + 1) - mCellWidth / 2;
            val y =mCellWidth / 2 ;
            if(isHorizontal){
                canvas.drawCircle(
                   x ,
                    y ,
                    50f,
                    lineBackgroundPaint
                )
            }else{
                canvas.drawCircle(
                    y,
                    x,
                    50f,
                    lineBackgroundPaint
                )

                AppCompatResources.getDrawable(
                    context,
                    R.drawable.baseline_backpack_24
                )?.toBitmap()?.let {
                    canvas.drawBitmap(Bitmap.createBitmap(it),y,x,lineBackgroundPaint)
                }


            }


        }
    }

    private fun createLineIndicator(canvas: Canvas) {

        var startCenterXY:Float = 0f
        var endCenterXY:Float = 0f

        startCenterXY = mCellWidth/2
        endCenterXY = mCellWidth* PROGRESS_COUNT - (mCellWidth/2)
        if(isHorizontal){
            canvas.drawLine(startCenterXY,mCellWidth/2,endCenterXY,mCellWidth/2,lineBackgroundPaint)
        }else{
            canvas.drawLine(mCellWidth/2,startCenterXY,mCellWidth/2,endCenterXY,lineBackgroundPaint)
        }

    }


}