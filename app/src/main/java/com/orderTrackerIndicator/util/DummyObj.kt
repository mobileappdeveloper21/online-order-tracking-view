package com.orderTrackerIndicator.util

import android.content.Context
import com.orderTrackerIndicator.R
import com.orderTrackerIndicator.custom.model.TrackerStateProgressData

object DummyObj {

    fun verticalOrderListData(context:Context):ArrayList<TrackerStateProgressData>{

        val trackerStateProgressList = arrayListOf<TrackerStateProgressData>()

        trackerStateProgressList.add(TrackerStateProgressData(
            trackingName = "Order Placed",
            trackingSubDetail ="Order Processed" ,
            trackerIconBitmap = context.getDrawable(R.drawable.shopping_cart)?.toBitmap(),
            trackingDate = "18 May 2024",
            trackerProgressPercentage = 100
            ))

        trackerStateProgressList.add(TrackerStateProgressData(
            trackingName ="Order Shipped" ,
            trackingSubDetail = "Order Shipped to Warehouse",
            trackerIconBitmap = context.getDrawable(R.drawable.store)?.toBitmap() ,
            trackingDate ="20 May 2024",
            trackerProgressPercentage = 100
            ))

        trackerStateProgressList.add(TrackerStateProgressData(
            trackingName = "Order Dispatched",
            trackingSubDetail = "Order Dispatched to Your Location",
            trackerIconBitmap = context.getDrawable(R.drawable.fast_delivery)?.toBitmap(),
            trackingDate ="22 May 2024",
            trackerProgressPercentage = 100))

        trackerStateProgressList.add(TrackerStateProgressData(trackingName = "Order Reached",
            trackingSubDetail ="Order Successfully Delivery" ,
            trackerIconBitmap =context.getDrawable(R.drawable.check_mark)?.toBitmap() ,
            trackingDate ="23 May 2024",
            trackerProgressPercentage = 0 , statusIcon = context.getDrawable(R.drawable.shopping_cart)?.toBitmap() ))

        return trackerStateProgressList
    }

}