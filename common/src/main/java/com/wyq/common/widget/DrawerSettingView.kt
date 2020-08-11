package com.wyq.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.R
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * 首页打开侧滑面板按钮
 */
class DrawerSettingView : View {

    companion object {
        const val TAG = "AdditionNoteView"
    }

    private var startX by Delegates.notNull<Float>()
    private var stopX by Delegates.notNull<Float>()

    private var mViewWidth: Float = 0f
    private var mViewHeight: Float = 0f
    private var linePaint: Paint = Paint()
    private var circlePaint: Paint = Paint()

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
        linePaint.strokeWidth = SizeUtils.dp2px(2f).toFloat()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mViewWidth = width.toFloat()
        mViewHeight = height.toFloat()
        Timber.d("onLayout: $width")

        startX = SizeUtils.dp2px(8f).toFloat()
        stopX = mViewWidth - SizeUtils.dp2px(8f)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            (mViewWidth / 2),
            (mViewHeight / 2),
            (mViewWidth / 2),
            circlePaint
        )

        canvas.drawLine(
            startX,
            mViewHeight / 2 - SizeUtils.dp2px(4f),
            stopX,
            mViewHeight / 2 - SizeUtils.dp2px(4f),
            linePaint
        )
        //中间
        canvas.drawLine(startX, mViewHeight / 2, stopX, mViewHeight / 2, linePaint)

        canvas.drawLine(
            startX,
            mViewHeight / 2 + SizeUtils.dp2px(4f),
            stopX,
            mViewHeight / 2 + SizeUtils.dp2px(4f),
            linePaint
        )
    }

}

