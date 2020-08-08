package com.wyq.common.ext

import com.wyq.common.BuildConfig

fun throwExceptionWhenDebug(message: String? = "", cause: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        throw RuntimeException(message, cause)
    }
}
