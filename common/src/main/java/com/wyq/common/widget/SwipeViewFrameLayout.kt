package com.wyq.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper

class SwipeViewFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    companion object {
        const val TAG = "SwipeViewFrameLayout"
    }

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mDragRange: Int = 0
    private val dragHelper: ViewDragHelper
    private lateinit var mContentView: ViewGroup
    private lateinit var mDeleteView: ViewGroup
    private val minVelocity: Float
    private var mSwipeEnabled: Boolean = true
    private var mStatus = Status.CLOSED

    init {
        dragHelper = ViewDragHelper.create(this, 1.0f, SwipeViewDragHelper())
        minVelocity = dragHelper.minVelocity
    }

    fun setSwipeEnable(enable: Boolean) {
        this.mSwipeEnabled = enable
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mContentView = getChildAt(0) as ViewGroup
        mDeleteView = getChildAt(1) as ViewGroup
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mDragRange = mDeleteView.measuredWidth
        mWidth = w
        mHeight = h
        Log.d(TAG, "onSizeChanged: $mWidth    , mDragRange:  $mDragRange")
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mDeleteView.layout(right, 0, right + mDeleteView.width, mDeleteView.height)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (!mSwipeEnabled) return super.onInterceptTouchEvent(ev)
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mSwipeEnabled) return super.onTouchEvent(event)
        dragHelper.processTouchEvent(event)
        return true
    }

    inner class SwipeViewDragHelper : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {

            return child == mContentView || child == mDeleteView
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            Log.d(TAG, "getViewHorizontalDragRange: " + mWidth)
            return mDragRange
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            Log.d(TAG, "clampViewPositionHorizontal: " + left)
            var dragDistance = left
            if (child == mContentView) {
                if (left > 0) {
                    dragDistance = 0
                } else if (left < -mDragRange) {
                    dragDistance = -mDragRange
                }
            } else {
                if (left < mWidth - mDragRange) {
                    dragDistance = mWidth - mDragRange
                } else if (left > mWidth) {
                    dragDistance = mWidth
                }
            }
            return dragDistance
        }


        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {

            Log.e(TAG, "left:" + left + " top:" + top + "  dx:" + dx)
            Log.e(
                TAG,
                "deleteView.getLeft()：" + mDeleteView.getLeft() + "   deleteView.getRight():" + mDeleteView.getRight()
            )
            mStatus = Status.DRAGGING
            if (dx != 0) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (changedView == mContentView) {
                mDeleteView.offsetLeftAndRight(dx)
            } else {
                mContentView.offsetLeftAndRight(dx)
            }
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            Log.d(
                TAG,
                "releasedChild:" + releasedChild + " xvel:" + xvel + " minVelocity: " + minVelocity
                        + "  mContentView.left: " + mContentView.left
            )
            handleViewReleased(releasedChild, xvel, yvel)
        }

        private fun handleViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            when {
                xvel > minVelocity -> {
                    close()
                }

                xvel < -minVelocity -> {
                    open()
                }
                else -> {
                    handleViewReleased()
                }
            }
        }

        private fun handleViewReleased() {
            if (mContentView.left > -(mDragRange / 2)) {
                close()
            } else {
                open()
            }
        }
    }

    private fun open() {
        mStatus = Status.OPENED
        val left = -mDragRange
        layoutContent(left)
    }

    private fun close() {
        mStatus = Status.CLOSED
        val left = 0
        layoutContent(left)
    }

    private fun layoutContent(left: Int) {
        if (dragHelper.smoothSlideViewTo(mContentView, left, 0)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    fun isOpening() = mStatus != Status.CLOSED

    enum class Status {
        CLOSED, OPENED, DRAGGING
    }

    interface OnSwipeListener {

    }
}
