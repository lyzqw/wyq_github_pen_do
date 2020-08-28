package com.wyq_github_pen_do.fragment

import com.wyq.common.base.BaseFragment
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.databinding.FragmentNoteDetailBinding


class PendingFragment : BaseFragment<FragmentNoteDetailBinding>() , INoteFragment {


    companion object {
        fun newInstance() = PendingFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_pending

    override fun initView() {

    }


    override fun initData() {

    }

    override fun initListener() {
    }

    override fun insertLatestNote(note: NoteListBean) {

    }

    override fun OnItemClickListener(note: NoteListBean) {

    }


}