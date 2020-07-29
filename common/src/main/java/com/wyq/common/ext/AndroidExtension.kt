package com.wyq.common.ext

import android.os.Looper


fun isMainThread() = Looper.myLooper() == Looper.getMainLooper()
