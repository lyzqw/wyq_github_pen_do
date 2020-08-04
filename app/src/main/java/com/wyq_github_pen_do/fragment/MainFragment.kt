package com.wyq_github_pen_do.fragment

import com.wyq.common.base.BaseFragment
import com.wyq.common.model.DefaultNoteConfig
import com.wyq.common.model.Note
import com.wyq.common.model.NoteFactory
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.activity.NoteActivity
import com.wyq_github_pen_do.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView() {
        new_note.setOnClickListener {

            val factory: Note.Factory =
                NoteFactory(UUID.randomUUID().toString(), DefaultNoteConfig.EditMode.EDIT_AUTO_CAN)
            NoteActivity.start(factory.build())
        }
    }

    override fun initData() {

    }


}