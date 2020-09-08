package com.wyq.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout


class DeleteFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("wqq", "onLayout.left: "+getChildAt(0).left)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d("wqq", "onFinishInflate.left: "+getChildAt(0).left)
    }
}