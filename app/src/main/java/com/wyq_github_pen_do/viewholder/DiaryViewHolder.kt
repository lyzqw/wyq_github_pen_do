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
            layoutNoteContent.tvNoteContent.text =
                if (noteListBean.content.isNullOrEmpty()) noteListBean.title.value() else noteListBean.content.value()

            val noteCreateDateMs = noteListBean.create_date?.toLongOrNull().value()
            if (noteCreateDateMs <= 0) return
            val time =
                TimeUtils.millis2String(noteCreateDateMs, TimeUtils.getSafeDateFormat("HH:mm"))
            val date =
                TimeUtils.millis2String(noteCreateDateMs, TimeUtils.getSafeDateFormat("MM/dd"))
            val noteDate = time + "\n" + date + TimeUtils.getChineseWeek(noteCreateDateMs)
            layoutNoteDate.tvNoteDate.text = noteDate
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
