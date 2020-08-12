package com.wyq_github_pen_do.databing

import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ColorUtils
import com.wyq.common.widget.AdditionNoteView


@BindingAdapter("setEditContent")
fun setEditContent(view: View?, text: String?) {
    Log.d("wqq", "setEditContent: ${text}")
}


@BindingAdapter("setMainAddNoteBackground")
fun setMainAddNoteBackground(view: AdditionNoteView?, @ColorRes color: Int?) {
    view ?: return
    color ?: return
    view.setCircleBackgroundColor(ColorUtils.getColor(color))
}