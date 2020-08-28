package com.wyq_github_pen_do.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.wyq.common.base.BaseViewHolder
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.throwExceptionWhenDebug
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.fragment.DiaryFragment
import com.wyq_github_pen_do.viewholder.DiaryViewHolder

class NoteListAdapter(private val iNoteFragment: INoteFragment) :
    PagingDataAdapter<NoteListBean, BaseViewHolder>(NoteListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            NoteTypeEnum.TYPE_DIARY_STYLE_1.code -> {
                return DiaryViewHolder.createStyleLeft(parent)
            }
            NoteTypeEnum.TYPE_DIARY_STYLE_2.code -> {
                return DiaryViewHolder.createStyleRight(parent)
            }
            else -> return DiaryViewHolder.createStyleLeft(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        item ?: return
        when (holder) {
            is DiaryViewHolder -> {
                holder.bindData(item)
                holder.itemView.clickJitter { iNoteFragment.OnItemClickListener(item) }
            }
            else -> throwExceptionWhenDebug("This is error holder!")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type ?: 0
    }

    class NoteListDiffCallback : DiffUtil.ItemCallback<NoteListBean>() {

        override fun areItemsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem == newItem

    }
}