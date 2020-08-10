package com.wyq.common.ext

import android.os.Looper



const val MAIN_BOTTOM_CORNER_SIZE = 16f

fun isMainThread() = Looper.myLooper() == Looper.getMainLooper()
