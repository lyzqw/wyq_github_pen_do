package com.wyq.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SmartRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    companion object {
        const val TAG = "wqq_rv"
    }

    private var mIsBeingDragged: Boolean = false
    private var mEnableLoadMore: Boolean = false
    private var mOnLoadMoreListener: ((RecyclerView) -> Unit)? = null
    private var preloadPosition = 5

    init {

        addOnScrollListener(SmartOnScrollListener())
    }


    fun finishLoadMore() {
        mIsBeingDragged = false
    }

    fun finishLoadMoreWithNoMoreData() {
        mEnableLoadMore = false
        mIsBeingDragged = false
    }

    fun canLoadMore(can: Boolean) {
        mEnableLoadMore = can
    }

    fun setOnLoadMoreListener(listener: (RecyclerView) -> Unit) {
        mOnLoadMoreListener = listener
    }

    inner class SmartOnScrollListener : OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            //上拉
            if (dy < 0 || mOnLoadMoreListener == null) return

            //能上拉
            var lastVisiblePosition = findLastVisibleItemPosition(recyclerView)

            Log.d(TAG, "onScrolled.lastVisiblePosition: " + lastVisiblePosition)
            Log.d(TAG, "onScrolled.itemCount: " + getItemCount(recyclerView))

            val shouldLoadMore =
                lastVisiblePosition > 0 && lastVisiblePosition >= getItemCount(recyclerView) - preloadPosition - 1


            //正在上拉
            if (!mIsBeingDragged && shouldLoadMore) {
                mIsBeingDragged = true
                mOnLoadMoreListener?.invoke(this@SmartRecyclerView)
            }
            //预加载
        }

        private fun findLastVisibleItemPosition(recyclerView: RecyclerView): Int {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            return linearLayoutManager.findLastVisibleItemPosition()
        }

        private fun getItemCount(recyclerView: RecyclerView): Int {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            return linearLayoutManager.itemCount
        }
    }
}