package com.wyq.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.R
import timber.log.Timber

class AdditionNoteView : View {

    companion object {
        const val TAG = "AdditionNoteView"
    }

    private var wRectF: RectF = RectF()
    private var hRectF: RectF = RectF()
    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0
    private var circlePaint: Paint = Paint()
    private var linePaint: Paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        circlePaint.style = Paint.Style.STROKE
        circlePaint.color = resources.getColor(R.color.gray_999999)
        circlePaint.style = Paint.Style.FILL
        circlePaint.isAntiAlias = true

        linePaint.color = Color.WHITE
        linePaint.isAntiAlias = true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mViewWidth = width
        mViewHeight = height
        Timber.d("onLayout: $width")
        val left = SizeUtils.dp2px(8f).toFloat()
        val right = mViewWidth - SizeUtils.dp2px(8f).toFloat()
        val top = mViewHeight / 2 - SizeUtils.dp2px(2f).toFloat()
        val bottom = mViewHeight / 2 + SizeUtils.dp2px(2f).toFloat()
        wRectF.set(left, top, right, bottom)

        hRectF.set(
            mViewWidth / 2 - SizeUtils.dp2px(2f).toFloat(),
            left,
            mViewWidth / 2 + SizeUtils.dp2px(2f).toFloat(),
            mViewHeight - SizeUtils.dp2px(8f).toFloat()
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: " + width)
        canvas.drawCircle(
            (mViewWidth / 2).toFloat(),
            (mViewHeight / 2).toFloat(),
            (mViewWidth / 2).toFloat(),
            circlePaint
        )

        canvas.drawRoundRect(
            wRectF,
            SizeUtils.dp2px(10f).toFloat(), SizeUtils.dp2px(10f).toFloat(), linePaint
        )
        canvas.drawRoundRect(
            hRectF,
            SizeUtils.dp2px(10f).toFloat(), SizeUtils.dp2px(10f).toFloat(), linePaint
        )
    }

    fun setCircleBackgroundColor(@ColorInt color: Int) {
        circlePaint.color = color
        invalidate()
    }
}

