package com.wyq.common.ext

import android.view.View


const val TIME_TAG_KEY_TRIGGER_LAST = 1111111111
const val TIME_TAG_KEY_TRIGGER_DELAY = 1111111112

/**
 * 带过滤的点击事件 默认300毫秒
 */
fun View.clickJitter(time: Long = 300, block: (View) -> Unit) {
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it)
        }
    }
}

private var View.triggerLastTime: Long
    get() = if (getTag(TIME_TAG_KEY_TRIGGER_LAST) != null) getTag(TIME_TAG_KEY_TRIGGER_LAST) as Long else 0
    set(value) {
        setTag(TIME_TAG_KEY_TRIGGER_LAST, value)
    }

private var View.triggerDelay: Long
    get() = if (getTag(TIME_TAG_KEY_TRIGGER_DELAY) != null) getTag(TIME_TAG_KEY_TRIGGER_DELAY) as Long else 500
    set(value) {
        setTag(TIME_TAG_KEY_TRIGGER_DELAY, value)
    }

private fun View.clickEnable(): Boolean {
    return System.currentTimeMillis() - triggerLastTime >= triggerDelay.also {
        triggerLastTime = System.currentTimeMillis()
    }
}