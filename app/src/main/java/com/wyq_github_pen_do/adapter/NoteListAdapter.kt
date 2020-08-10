package com.wyq_github_pen_do.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wyq.common.base.BaseViewHolder
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.throwExceptionWhenDebug
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.viewholder.DiaryViewHolder

class NoteListAdapter : ListAdapter<NoteListBean, BaseViewHolder>(NoteListDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            NoteTypeEnum.TYPE_DIARY_STYLE_1.code -> {
                return DiaryViewHolder.create(parent)
            }
            else -> return DiaryViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is DiaryViewHolder -> {
                holder.bindData(currentList[position])
            }
            else -> throwExceptionWhenDebug("This is error holder!")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return currentList[position].type
    }

    fun setNewData(dataList: List<NoteListBean>) {
        submitList(dataList)
    }

    fun addData(dataList: ArrayList<NoteListBean>) {
        submitList(dataList)
    }

    class NoteListDiffCallback : DiffUtil.ItemCallback<NoteListBean>() {

        override fun areItemsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem == newItem

    }
}