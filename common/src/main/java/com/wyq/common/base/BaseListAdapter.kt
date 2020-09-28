package com.wyq.common.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<VH : BaseViewHolder, T>(var dataList: MutableList<T> = ArrayList()) :
    RecyclerView.Adapter<VH>() {


    open fun addData(position: Int, t: T) {
        dataList.add(position, t)
        notifyItemInserted(position)
    }
}