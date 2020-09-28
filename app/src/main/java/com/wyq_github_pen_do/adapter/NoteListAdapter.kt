package com.wyq_github_pen_do.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyq.common.base.BaseListAdapter
import com.wyq.common.base.BaseViewHolder
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.throwExceptionWhenDebug
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.fragment.DiaryFragment
import com.wyq_github_pen_do.viewholder.DiaryViewHolder
import kotlinx.android.synthetic.main.item_note_normal_style_1.view.*

class NoteListAdapter(private val iNoteFragment: INoteFragment) :
    BaseListAdapter<BaseViewHolder,NoteListBean>() {


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
        Log.d("wqq", "onBindViewHolder: 位置 "+position +" 数量: "+itemCount)
        val item = getItem(position)
        when (holder) {
            is DiaryViewHolder -> {
                holder.bindData(item)
                holder.itemView.cl_item.clickJitter { iNoteFragment.OnItemClickListener(item) }
            }
            else -> throwExceptionWhenDebug("This is error holder!")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    private fun getItem(position: Int) = dataList[position]

    fun setNewData(list: MutableList<NoteListBean>) {
        dataList = list
    }

    fun addData(list: List<NoteListBean>) {
        dataList.addAll(list)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    class NoteListDiffCallback : DiffUtil.ItemCallback<NoteListBean>() {
        override fun areItemsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(oldItem: NoteListBean, newItem: NoteListBean): Boolean =
            oldItem == newItem

    }
}