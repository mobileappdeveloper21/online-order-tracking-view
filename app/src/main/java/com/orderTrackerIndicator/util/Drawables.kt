package com.orderTrackerIndicator.util

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.Log
import androidx.core.graphics.drawable.toBitmap

@SuppressLint("NewApi")
internal fun Drawable.toBitmap(): Bitmap {

    return when (this) {
        is BitmapDrawable -> bitmap
        is VectorDrawable -> toBitmap()
        is AdaptiveIconDrawable ->{
            this.toBitmap()
        }
        else -> throw IllegalArgumentException("Unsupported drawable type")
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
internal fun VectorDrawable.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}