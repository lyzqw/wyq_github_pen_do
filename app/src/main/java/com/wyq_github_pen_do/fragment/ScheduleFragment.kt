package com.wyq_github_pen_do.fragment

import com.wyq.common.base.BaseFragment
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.adapter.NoteListAdapter
import com.wyq_github_pen_do.databinding.FragmentNoteDetailBinding
import org.koin.android.ext.android.inject


class ScheduleFragment : BaseFragment<FragmentNoteDetailBinding>() {


    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_note

    override fun initView() {

    }


    override fun initData() {

    }

    override fun initListener() {
    }


}