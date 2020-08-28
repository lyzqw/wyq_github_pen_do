package com.wyq_github_pen_do.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseViewHolder
import com.wyq.common.ext.value
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.databinding.ItemNoteNormalStyle1Binding
import com.wyq_github_pen_do.databinding.ItemNoteNormalStyle2Binding
import kotlinx.android.synthetic.main.item_note_left_date.view.*
import kotlinx.android.synthetic.main.item_note_right_mixture.view.*

class DiaryViewHolder(private val rootView: View) : BaseViewHolder(rootView) {


    fun bindData(noteListBean: NoteListBean) {
        rootView.tv_note_content.text =
            if (noteListBean.content.isNullOrEmpty()) noteListBean.title.value() else noteListBean.content.value()

        val noteCreateDateMs = noteListBean.create_date?.toLongOrNull().value()
        if (noteCreateDateMs <= 0) return
        val time =
            TimeUtils.millis2String(noteCreateDateMs, TimeUtils.getSafeDateFormat("HH:mm"))
        val date =
            TimeUtils.millis2String(noteCreateDateMs, TimeUtils.getSafeDateFormat("MM/dd"))
        val noteDate = time + "\n" + date + TimeUtils.getChineseWeek(noteCreateDateMs)
        rootView.tv_note_date.text = noteDate

    }

    companion object {
        fun createStyleLeft(parent: ViewGroup): DiaryViewHolder {
            val binding = ItemNoteNormalStyle1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DiaryViewHolder(binding.root)
        }

        fun createStyleRight(parent: ViewGroup): DiaryViewHolder {
            val binding = ItemNoteNormalStyle2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DiaryViewHolder(binding.root)
        }
    }

}
