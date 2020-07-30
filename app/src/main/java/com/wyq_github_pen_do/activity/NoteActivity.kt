package com.wyq_github_pen_do.activity

import com.wyq.common.base.BaseActivity
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivityNoteBinding
import com.wyq_github_pen_do.fragment.NoteFragment

/**
 * 编辑标签页
 */
class NoteActivity : BaseActivity<ActivityNoteBinding>() {

    override val layoutId: Int = R.layout.activity_note

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.note_container, NoteFragment(), "note_fragment").commitAllowingStateLoss()
    }

    override fun loadData() {

    }


}