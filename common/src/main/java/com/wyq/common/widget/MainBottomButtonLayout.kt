package com.wyq.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.R
import com.wyq.common.ext.getShapeDrawable
import kotlinx.android.synthetic.main.layout_main_bottom_button.view.*

class MainBottomButtonLayout : FrameLayout {

    companion object {
        const val TAG = "MainBottomButtonLayout"
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_main_bottom_button, this, true)

        val shapeDrawable = getShapeDrawable(
            SizeUtils.dp2px(16f).toFloat(),
            ColorUtils.getColor(R.color.gray_C3C3C3)
        )
        tv_diary.background = shapeDrawable
        tv_note.background = shapeDrawable
        tv_pending.background = shapeDrawable
        tv_schedule.background = shapeDrawable

    }

}

