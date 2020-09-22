package com.wyq.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Scroller
import com.wyq.common.ext.value

class OverScrollLayout : FrameLayout {

    companion object {
        const val TAG = "wqq"
    }

    private lateinit var lastInterceptMotionEvent: MotionEvent
    private var lastMotionEvent: MotionEvent? = null

    private var direct: Direct = Direct.NOTHING

    private val mScroller: Scroller = Scroller(context)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var intercept = false
        Log.d(TAG, "onInterceptTouchEvent: " + ev.action)
        if (ev.action == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onInterceptTouchEvent: 拦截 down  ")
            intercept = false
        } else if (ev.action == MotionEvent.ACTION_MOVE) {
            intercept = isMoving(ev, lastInterceptMotionEvent)
        }

        lastInterceptMotionEvent = MotionEvent.obtain(ev)
        Log.d(TAG, "onInterceptTouchEvent ==  intercept: " + intercept)
        return intercept
    }

    private fun isMoving(curr: MotionEvent, last: MotionEvent): Boolean {
        var dx = curr.rawX - last.rawX
        var dy = curr.rawY - last.rawY
        Log.d(TAG, "isMoving.dy:" + dy)
        return Math.abs(dx) > 8 || Math.abs(dy) > 8
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                direct = Direct.NOTHING
                lastMotionEvent = MotionEvent.obtain(event)
            }
            MotionEvent.ACTION_MOVE -> {
                if (lastMotionEvent == null) {
                    lastMotionEvent = MotionEvent.obtain(event)
                    return true
                }
                val dx = event.rawX - lastMotionEvent?.rawX.value() //left 正, right 负
                val dy = event.rawY - lastMotionEvent?.rawY.value() //下拉 正 , 上拉 负
                Log.d(TAG, "move: dx: $dx ======  dy: $dy")

                if (isTouchVertical(dy, dx)) {
                    if (dy > 0) {
                        direct = Direct.UPFETCH
                    } else {
                        direct = Direct.LOADMORE
                    }
                } else if (isTouchHorizontal(dx, dy)) {
                    if (dx > 0) {
                        direct = Direct.LEFT
                    } else {
                        direct = Direct.RIGHT
                    }
                }
                if (direct == Direct.NOTHING) return true

                if (isUpFetch(dy) || isLoadMore(dy)) {
                    smoothScrollBy(0, dy = -dy.toInt())
                }else if (isLeft(dx) || isRight(dx)){
                    smoothScrollBy(dx = -dx.toInt(), dy = 0)
                }
                lastMotionEvent = MotionEvent.obtain(event)
            }
            MotionEvent.ACTION_UP and MotionEvent.ACTION_CANCEL -> {
                lastMotionEvent = null
                direct = Direct.NOTHING
                smoothScrollTo(0, 0)
            }

        }
        return true
    }

    private fun isTouchVertical(dy: Float, dx: Float) =
        direct == Direct.NOTHING && (dy > 0 || dy < 0) && Math.abs(dy) > Math.abs(dx)

    private fun isTouchHorizontal(dx: Float, dy: Float) =
        direct == Direct.NOTHING && (dx > 0 || dx < 0) && Math.abs(dx) > Math.abs(dy)

    private fun isRight(dx: Float) = direct == Direct.RIGHT && dx < 0

    private fun isLeft(dx: Float) = direct == Direct.LEFT && dx > 0

    private fun isLoadMore(dy: Float) = direct == Direct.LOADMORE && dy < 0

    private fun isUpFetch(dy: Float) = direct == Direct.UPFETCH && dy > 0

    private fun smoothScrollTo(fx: Int, fy: Int) {
        val x = fx - mScroller.finalX
        val y = fy - mScroller.finalY
        smoothScrollBy(x, y)
    }

    private fun smoothScrollBy(dx: Int, dy: Int) {
        Log.d(TAG, "smoothScrollBy: dx: $dx ======  dy: $dy")
        mScroller.startScroll(mScroller.finalX, mScroller.finalY, dx, dy)
        invalidate()
    }


    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
        super.computeScroll()
    }

    enum class Direct {
        NOTHING,
        UPFETCH,
        LOADMORE,
        LEFT,
        RIGHT
    }
}

