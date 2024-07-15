package com.orderTrackerIndicator.custom.trackerStatiesProgress

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.orderTrackerIndicator.R
import com.orderTrackerIndicator.custom.model.TrackerStateProgressData
import com.orderTrackerIndicator.databinding.TrackerStatusProgressViewBinding
import com.orderTrackerIndicator.databinding.VerticalStatusProgressBinding
import com.orderTrackerIndicator.util.toBitmap


class TrackerStatesProgressbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        TrackerStatusProgressViewBinding.inflate(LayoutInflater.from(context), this, true)

    private val DEFAULT_TEXT_SIZE = 2f
    private val DEFAULT_SUB_TEXT_SIZE = 3f
    private val DEFAULT_CONTTAINER_MARGIN = 2f
    private val DEFFAULT_TRACKER_INDICATOR_SIZE = 10f
    private val DEFFAULT_TRACKER_LINE_INDICATOR_SIZE = 1f
    private val DEFFAULT_TRACKER_DATE_STATUS_SIZE = 10f


    private var viewType = 0 //Horizontal  = 0 ,Vertical = 1
    private var trackerProgressTitleSize:Float =DEFAULT_TEXT_SIZE
    private var trackerProgressSubTitleSize:Float =DEFAULT_SUB_TEXT_SIZE
    private var trackerProgressTitleColor:Int
    private var trackerIndicatorWorkingBgColor:Int
    private var trackerIndicatorCompletedBgColor:Int
    private var trackerProgressSubTitleColor:Int
    private var trackerDateStatusColor:Int
    private var progressTrackerColor:Int
    private var progressColor:Int
    private var trackerProgressContainerMargin:Float =DEFAULT_CONTTAINER_MARGIN
    private var isStatusIconEnable = false
    private var isDateTxtEnable = false
    private var isTrackerIndicatorIconEnable = false
    private var trackProgressEnableOnTouch = false
    private var trackCompletedIcon: Bitmap? = null
    private var trackWorkingIcon: Bitmap? = null
    private var statusIcon: Bitmap? = null
    private var trackerIndicatorWidth :Float =DEFFAULT_TRACKER_INDICATOR_SIZE
    private var trackerDateStatusSize :Float =DEFFAULT_TRACKER_DATE_STATUS_SIZE
    private var progressLineScale:Float = DEFFAULT_TRACKER_LINE_INDICATOR_SIZE



    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TrackerStatesProgressbar)
        trackerProgressTitleColor =  ContextCompat.getColor(context, R.color.black);
        trackerProgressSubTitleColor = ContextCompat.getColor(context, R.color.black );
        trackerIndicatorWorkingBgColor = ContextCompat.getColor(context,R.color.black)
        trackerIndicatorCompletedBgColor = ContextCompat.getColor(context,R.color.black)
        trackerDateStatusColor = ContextCompat.getColor(context,R.color.black)
        progressTrackerColor = ContextCompat.getColor(context,R.color.black)
        progressColor = ContextCompat.getColor(context,R.color.black)


        try {
            viewType = typeArray.getInt(R.styleable.TrackerStatesProgressbar_view_type, 0)
            trackerProgressTitleSize = typeArray.getDimension(R.styleable.TrackerStatesProgressbar_trackerProgressTitleSize,trackerProgressTitleSize)
            trackerProgressSubTitleSize = typeArray.getDimension(R.styleable.TrackerStatesProgressbar_trackerProgressSubTitleSize,trackerProgressSubTitleSize)
            trackerDateStatusSize = typeArray.getDimension(R.styleable.TrackerStatesProgressbar_trackerDateStatusSize,trackerDateStatusSize)
            progressLineScale = typeArray.getFloat(R.styleable.TrackerStatesProgressbar_progressLineScale,progressLineScale)
            trackerIndicatorWidth = typeArray.getDimension(R.styleable.TrackerStatesProgressbar_trackerIndicatorContainerSize,trackerIndicatorWidth)
            trackerProgressTitleColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_trackerProgressTitleColor,trackerProgressTitleColor)
            trackerProgressSubTitleColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_trackerProgressSubTitleColor,trackerProgressTitleColor)
            trackerIndicatorWorkingBgColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_trackerIndicatorWorkingBgColor,trackerIndicatorWorkingBgColor)
            trackerIndicatorCompletedBgColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_trackerIndicatorCompletedBgColor,trackerIndicatorCompletedBgColor)
            trackerDateStatusColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_trackerDateStatusColor,trackerDateStatusColor)
            progressColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_ProgressColor,progressColor)
            progressTrackerColor = typeArray.getColor(R.styleable.TrackerStatesProgressbar_ProgressTrackerColor,progressTrackerColor)
            trackerProgressContainerMargin = typeArray.getDimension(R.styleable.TrackerStatesProgressbar_trackerProgressContainerMargin,trackerProgressContainerMargin)
            isStatusIconEnable = typeArray.getBoolean(R.styleable.TrackerStatesProgressbar_enable_StatusIcon,false)
            trackProgressEnableOnTouch = typeArray.getBoolean(R.styleable.TrackerStatesProgressbar_trackProgressEnableOnTouch,false)
            isDateTxtEnable = typeArray.getBoolean(R.styleable.TrackerStatesProgressbar_enable_status_dateTxt,false)
            isTrackerIndicatorIconEnable = typeArray.getBoolean(R.styleable.TrackerStatesProgressbar_isTrackerIndicatorIcon,false)
            trackCompletedIcon = typeArray.getDrawable(R.styleable.TrackerStatesProgressbar_trackerIndicatorCompletedIcon)?.toBitmap()
            trackWorkingIcon = typeArray.getDrawable(R.styleable.TrackerStatesProgressbar_trackerIndicatorWorkingIcon)?.toBitmap()
            statusIcon = typeArray.getDrawable(R.styleable.TrackerStatesProgressbar_statusIcon)?.toBitmap()

        } catch (e: Exception) {
        } finally {
            typeArray.recycle()
        }
    }


    public fun addTrackerViews(trackerList: ArrayList<TrackerStateProgressData>) {

        if(viewType == 0){
            binding.trackerStatusProgressContainer.orientation = LinearLayout.HORIZONTAL
            for (v in trackerList) {
                binding.trackerStatusProgressContainer.addView(createHorizontalView(v))
            }
        }else{

            binding.trackerStatusProgressContainer.orientation = LinearLayout.VERTICAL
            for (v in trackerList) {
                binding.trackerStatusProgressContainer.addView(createVerticalView(v))
            }
        }
    }



    private fun createHorizontalView(v: TrackerStateProgressData):View{
        val verticalBinding =
            VerticalStatusProgressBinding.inflate(LayoutInflater.from(context), null, false)
        return verticalBinding.root
    }

    private fun createVerticalView(trackerData: TrackerStateProgressData): View {
        val verticalBinding =
            VerticalStatusProgressBinding.inflate(LayoutInflater.from(context), null, false)
            verticalBinding.statusIcon.isVisible = isStatusIconEnable

            verticalBinding.indicatorStatusView.dateTxt.isVisible = isDateTxtEnable
            verticalBinding.statusTrackTitle.textSize = convertSpToPixel(trackerProgressTitleSize)
            verticalBinding.statusTrackSubDetail.textSize = convertSpToPixel(trackerProgressSubTitleSize)

            verticalBinding.statusTrackTitle.setTextColor(trackerProgressTitleColor)
            verticalBinding.statusTrackSubDetail.setTextColor(trackerProgressSubTitleColor)
            verticalBinding.indicatorStatusView.dateTxt.setTextColor(trackerDateStatusColor)

            /* Tracker Indicator  */
            if(isTrackerIndicatorIconEnable){

                if(trackWorkingIcon!=null){
                  verticalBinding.indicatorStatusView.indicatorIcon.setImageBitmap(trackWorkingIcon)
                 }
            }

            if(isStatusIconEnable){
                val statusBitmap = trackerData.statusIcon ?: statusIcon
                verticalBinding.statusIcon.setImageBitmap(statusBitmap)
            }

//            verticalBinding.indicatorStatusView.indicatorContainer.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, trackerIndicatorBackgroundColor) )
            val trackerIndicatorWidth = convertDpToPixel(trackerIndicatorWidth)
            val trackerIndicatorHeight = convertDpToPixel(this.trackerIndicatorWidth)
            val trackerIndicatorContainerLayout = verticalBinding.indicatorStatusView.indicatorContainer.layoutParams
            trackerIndicatorContainerLayout.height = trackerIndicatorWidth
            trackerIndicatorContainerLayout.width = trackerIndicatorHeight
            verticalBinding.indicatorStatusView.indicatorContainer.layoutParams = trackerIndicatorContainerLayout


            verticalBinding.statusTrackTitle.text = trackerData.trackingName
            verticalBinding.statusTrackSubDetail.text = trackerData.trackingSubDetail
            verticalBinding.indicatorStatusView.dateTxt.text = trackerData.trackingDate

           /* Set Track Progress */
           verticalBinding.indicatorStatusView.verticalProgress.progress = trackerData.trackerProgressPercentage
           verticalBinding.indicatorStatusView.verticalProgress.scaleX = progressLineScale

//            changeProgressBarColor(verticalBinding.indicatorStatusView.verticalProgress,progressTrackerColor,progressColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            verticalBinding.indicatorStatusView.verticalProgress.progressDrawable?.colorFilter =
                BlendModeColorFilter(progressTrackerColor, BlendMode.SRC_ATOP)
        } else {
            verticalBinding.indicatorStatusView.verticalProgress.progressDrawable?.setColorFilter(progressTrackerColor, PorterDuff.Mode.SRC_ATOP)
        }



        return verticalBinding.root
    }

    private fun changeProgressBarColor(
        verticalProgress: VerticalProgressBar,
        progressTrackerColor: Int,
        progressColor: Int
    ) {
        val trackColorInt = progressTrackerColor
        val progressColorInt = progressColor

        // Get the track drawable and apply color filter
        val trackDrawable = ContextCompat.getDrawable(context, R.drawable.custom_vertical_progress_bar_track)
        trackDrawable?.setColorFilter(trackColorInt, PorterDuff.Mode.SRC_IN)
        verticalProgress.progressDrawable = trackDrawable

        // Get the progress drawable and apply color filter
        val progressDrawable = ContextCompat.getDrawable(context, R.drawable.custom_vertical_progress_bar_progress)
        progressDrawable?.setColorFilter(progressColorInt, PorterDuff.Mode.SRC_IN)
        verticalProgress.progressDrawable = progressDrawable
    }

    private fun convertSpToPixel(sp: Float): Float {
        val scale = resources.configuration.fontScale
        return sp * scale;
    }

    private fun spToPx(sp: Int): Float {
        return (sp * resources.displayMetrics.density)
    }

    private fun convertDpToPixel(dp: Float): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale).toInt()
    }

    fun dpToPx( dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
}