package com.wyq_github_pen_do.fragment

import android.text.Editable
import cn.dreamtobe.kpswitch.util.KeyboardUtil
import com.wyq.common.base.BaseFragment
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.value
import com.wyq.common.model.NoteListBean
import com.wyq.common.widget.NoteTitleEditView
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.Listener.SimpleTextWatcherListener
import com.wyq_github_pen_do.activity.NoteDetailActivity
import com.wyq_github_pen_do.activity.NoteDetailActivity.Companion.KEY_AUTO_EDIT_NOTE
import com.wyq_github_pen_do.databinding.FragmentNoteDetailBinding
import com.wyq_github_pen_do.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * 编辑便签
 */
class NoteDetailFragment : BaseFragment<FragmentNoteDetailBinding>() {

    private val mViewModel by sharedViewModel<NoteViewModel>()

    override fun layoutId(): Int = R.layout.fragment_note_detail

    override fun initView() {
        binding.viewModel = mViewModel
        defaultTitleShowKeyboard()
        imageBack.clickJitter { activity?.finish() }
    }

    private fun defaultTitleShowKeyboard() {
        noteTitle.postDelayed({
            if (mViewModel.canAutoEdit.value.value()) {
                KeyboardUtil.showKeyboard(noteTitle)
            }
        }, 100)
    }

    override fun initData() {
        setPreviewNoteData()
    }

    private fun setPreviewNoteData() {
        val canAutoEdit = activity?.intent?.getBooleanExtra(KEY_AUTO_EDIT_NOTE, true).value()
        mViewModel.setCanAutoEdit(canAutoEdit)
        val noteListBean =
            activity?.intent?.getSerializableExtra(NoteDetailActivity.KEY_NOTE_BEAN) as? NoteListBean
        mViewModel.setNoteTitle(noteListBean?.title.value())
        mViewModel.setNoteContent(noteListBean?.content.value())
        mViewModel.setNoteTitleCreateDate(canAutoEdit, noteListBean?.create_date.value())
    }

    override fun initListener() {
        tvNoteContent.addTextChangedListener(object : SimpleTextWatcherListener() {
            override fun afterTextChanged(s: Editable?) {

            }
        })

        noteTitle.setNoteTileEditListener(object : NoteTitleEditView.NoteTileEditListener {
            override fun onTextChanged(newLine: Boolean) {
                if (newLine) {
                    KeyboardUtil.showKeyboard(tvNoteContent)
                    tvNoteContent.setSelection(tvNoteContent.length())
                }
            }
        })

        iv_star.setOnClickListener {
            mViewModel.loadData()
        }
    }
}