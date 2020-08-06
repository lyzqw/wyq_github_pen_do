package com.wyq.common.ext

import android.graphics.drawable.GradientDrawable


fun getShapeDrawable(radius: Float, color: Int): GradientDrawable {
    val normalBackground = GradientDrawable()
    normalBackground.cornerRadius = radius
    normalBackground.setColor(color)
    return normalBackground
}