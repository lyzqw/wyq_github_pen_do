package com.wyq.common.ext

import android.os.Looper
import android.widget.Toast
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.util.Util


const val MAIN_BOTTOM_CORNER_SIZE = 16f

fun isMainThread() = Looper.myLooper() == Looper.getMainLooper()


fun toast(text: String) =
    Toast.makeText(Utils.getApp().applicationContext, text, Toast.LENGTH_SHORT).show()
