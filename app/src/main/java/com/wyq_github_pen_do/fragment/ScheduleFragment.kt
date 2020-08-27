package com.wyq_github_pen_do.fragment

import com.wyq.common.base.BaseFragment
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.databinding.FragmentNoteDetailBinding


class ScheduleFragment : BaseFragment<FragmentNoteDetailBinding>() , INoteFragment {


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

    override fun insertLatestNote(note: NoteListBean) {

    }


}