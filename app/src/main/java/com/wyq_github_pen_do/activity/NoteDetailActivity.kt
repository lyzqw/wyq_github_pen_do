package com.wyq_github_pen_do.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.Utils
import com.wyq.common.base.BaseActivity
import com.wyq.common.ext.value
import com.wyq.common.model.NoteConfig
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.coroutine.NoteDetailScopedService
import com.wyq_github_pen_do.databinding.ActivityNoteBinding
import com.wyq_github_pen_do.fragment.NoteDetailFragment
import com.wyq_github_pen_do.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 编辑标签页
 */
class NoteDetailActivity : BaseActivity<ActivityNoteBinding>() {

    private var noteId: String = ""
    private var mainIndex: Int = 0
    private val mViewModel by viewModel<NoteViewModel>()

    companion object {
        const val KEY_NOTE_ID = "key_note_id"
        const val KEY_AUTO_EDIT_NOTE = "auto_edit_note"
        const val KEY_MAIN_INDEX = "key_main_index"
        const val KEY_NOTE_BEAN = "key_note_bean"

        fun start(config: NoteConfig) {
            val bundle = Bundle().also {
                it.putString(KEY_NOTE_ID, config.noteId())
                it.putBoolean(KEY_AUTO_EDIT_NOTE, config.autoEditNote())
                it.putInt(KEY_MAIN_INDEX, config.mainIndex())
                it.putSerializable(KEY_NOTE_BEAN, config.getPreviewNote())
            }
            val context = Utils.getApp().applicationContext
            val intent = Intent(context, NoteDetailActivity::class.java)
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override val layoutId: Int = R.layout.activity_note

    override fun initView() {
        noteId = intent?.getStringExtra(KEY_NOTE_ID).value()
        mainIndex = intent?.getIntExtra(KEY_MAIN_INDEX, 0).value()
        supportFragmentManager.beginTransaction()
            .add(R.id.note_container, NoteDetailFragment(), "note_fragment")
            .commitAllowingStateLoss()
    }

    override fun loadData() {
    }


    override fun finish() {
        super.finish()
        mViewModel.saveNoteWhenFinish(noteId, mainIndex, NoteDetailScopedService.getContinuation())
    }

    override fun initListener() {
    }

}