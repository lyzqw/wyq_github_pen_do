package com.wyq_github_pen_do.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseViewHolder
import com.wyq.common.ext.value
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.databinding.ItemNoteNormalBinding

class DiaryViewHolder(private val binding: ItemNoteNormalBinding) :
    BaseViewHolder(binding.root) {

    fun bindData(noteListBean: NoteListBean) {
        binding.apply {
            layoutNoteContent.tvNoteContent.text = noteListBean.content.value()
            layoutNoteDate.tvNoteDate.text = TimeUtils.millis2String(noteListBean.create_date?.toLongOrNull().value())
        }
    }

    companion object {
        fun create(parent: ViewGroup): DiaryViewHolder {
            val binding = ItemNoteNormalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DiaryViewHolder(binding)
        }
    }

}
