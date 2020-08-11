package com.wyq.common.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.R
import timber.log.Timber

/**
 * 虚线
 */
class DivisionView : View {

    companion object {
        const val TAG = "DivisionView"
    }

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0
    private var linePaint: Paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        linePaint.style = Paint.Style.STROKE
        linePaint.color = resources.getColor(R.color.white_255_alpha_30)
        linePaint.strokeWidth = SizeUtils.dp2px(1f).toFloat()
        linePaint.isAntiAlias = true
        linePaint.pathEffect = DashPathEffect(floatArrayOf(8f, 10f, 8f, 10f), 0f)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mViewWidth = width
        mViewHeight = height
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: " + width)
        canvas.drawLine(
            (mViewWidth / 2).toFloat(),
            0f,
            (mViewWidth / 2).toFloat(),
            mViewHeight.toFloat(),
            linePaint
        )
    }

}

