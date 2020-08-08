package com.wyq_github_pen_do.databing

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("setEditContent")
fun setEditContent(view: View?, text: String?) {
    Log.d("wqq", "setEditContent: ${text}")
}