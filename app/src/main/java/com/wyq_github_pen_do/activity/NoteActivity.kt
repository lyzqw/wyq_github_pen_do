package com.wyq_github_pen_do.activity

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.wyq.common.base.BaseActivity
import com.wyq.common.ext.value
import com.wyq.common.model.NoteConfig
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivityNoteBinding
import com.wyq_github_pen_do.fragment.NoteFragment
import com.wyq_github_pen_do.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 编辑标签页
 */
class NoteActivity : BaseActivity<ActivityNoteBinding>() {

    private var noteId: String = ""
    private val mViewModel by viewModel<NoteViewModel>()

    companion object {
        const val KEY_NOTE_ID = "key_note_id"
        const val AUTO_EDIT_NOTE = "auto_edit_note"

        fun start(config: NoteConfig) {
            val bundle = Bundle().also {
                it.putString(KEY_NOTE_ID, config.noteId())
                it.putBoolean(AUTO_EDIT_NOTE, config.autoEditNote())
            }
            ActivityUtils.startActivity(
                NoteActivity::class.java,
                bundle
            )
        }
    }

    override val layoutId: Int = R.layout.activity_note

    override fun initView() {
        noteId = intent?.getStringExtra(KEY_NOTE_ID).value()
        supportFragmentManager.beginTransaction()
            .add(R.id.note_container, NoteFragment(), "note_fragment").commitAllowingStateLoss()
    }

    override fun loadData() {
    }


    override fun finish() {
        super.finish()
        mViewModel.saveNoteWhenFinish(noteId)
    }

}