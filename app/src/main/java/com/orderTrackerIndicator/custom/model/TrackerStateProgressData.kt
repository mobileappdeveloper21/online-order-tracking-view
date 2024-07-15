package com.orderTrackerIndicator.custom.model

import android.graphics.Bitmap

data class TrackerStateProgressData(val trackingName:String?= null,
                                    val trackingSubDetail:String?= null,
                                    val trackerIconBitmap:Bitmap? = null,
                                    val trackerIconUrl:String? = null,
                                    val trackCompletedIcon: Bitmap? = null,
                                    val trackWorkingIcon: Bitmap?= null,
                                    val statusIcon:Bitmap?=null,
                                    val iconSize:Float? = null,
                                    val lineWidth:Float? =null,
                                    val showImageSize:Float? = null,
                                    val trackingDate:String? = null,
                                    val trackerProgressPercentage:Int


    )
