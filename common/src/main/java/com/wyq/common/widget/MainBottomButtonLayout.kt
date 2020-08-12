package com.wyq.common.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.R
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.ext.MAIN_BOTTOM_CORNER_SIZE
import com.wyq.common.ext.clickJitter
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


        val drawable = getNormalStatusDrawable()
        tv_diary.background = drawable
        tv_note.background = drawable
        tv_pending.background = drawable
        tv_schedule.background = drawable

    }

    private fun getNormalStatusDrawable(): Drawable {
        return getShapeDrawable(
            SizeUtils.dp2px(MAIN_BOTTOM_CORNER_SIZE).toFloat(),
            ColorUtils.getColor(R.color.gray_C3C3C3)
        )
    }

    private fun getSelectedStatusDrawable(dpValue: Float, @ColorRes color: Int): Drawable {
        return getShapeDrawable(
            SizeUtils.dp2px(dpValue).toFloat(),
            ColorUtils.getColor(color)
        )
    }


    fun setOnDiaryClickListener(callBack: (Boolean) -> Unit) {
        tv_diary.clickJitter(50) {
            Log.d(TAG, "setOnDiary: ")
            handleTabClickListener(it, callBack, MainTabEnum.TAB_DIARY)
        }
    }

    fun setOnNoteClickListener(callBack: (Boolean) -> Unit) {
        tv_note.clickJitter(50) {
            Log.d(TAG, "setOnNote: ")
            handleTabClickListener(it, callBack, MainTabEnum.TAB_NOTE)
        }
    }

    fun setOnPendingClickListener(callBack: (Boolean) -> Unit) {
        tv_pending.clickJitter(50) {
            Log.d(TAG, "setOnPending: ")
            handleTabClickListener(it, callBack, MainTabEnum.TAB_PENDING)
        }
    }

    fun setOnScheduleClickListener(callBack: (Boolean) -> Unit) {
        tv_schedule.clickJitter(50) {
            Log.d(TAG, "setOnSchedule: ")
            handleTabClickListener(it, callBack, MainTabEnum.TAB_SCHEDULE)
        }
    }

    private fun handleTabClickListener(
        targetView: View,
        callBack: (Boolean) -> Unit,
        tabEnum: MainTabEnum
    ) {
        val params = targetView.layoutParams
        val drawable = if (targetView.tag == true) {
            params.height = SizeUtils.dp2px(20f)
            getNormalStatusDrawable()
        } else {
            resetButtonStatus()
            params.height = SizeUtils.dp2px(23f)
            getSelectedStatusDrawable(8f, tabEnum.color)
        }
        targetView.layoutParams = params
        targetView.background = drawable
        targetView.tag = targetView.tag != true
        callBack.invoke(targetView.tag == true)
    }

    private fun resetButtonStatus() {
        val childCount = cl_bottom.childCount
        for (c in 0 until childCount) {
            val childView = cl_bottom.getChildAt(c)
            if (childView.tag == true) {
                val params = childView.layoutParams
                params.height = SizeUtils.dp2px(20f)
                childView.layoutParams = params
                childView.tag = childView.tag != true
                childView.background = getNormalStatusDrawable()
            }
        }

    }

}

