package com.wyq_github_pen_do.fragment

import android.text.Editable
import com.wyq.common.base.BaseFragment
import com.wyq.common.ext.value
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.`interface`.SimpleTextWatcherListener
import com.wyq_github_pen_do.activity.NoteDetailActivity.Companion.AUTO_EDIT_NOTE
import com.wyq_github_pen_do.databinding.FragmentNoteBinding
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
    }

    override fun initData() {
        mViewModel.setHasEditNote(activity?.intent?.getBooleanExtra(AUTO_EDIT_NOTE, true).value())
    }

    override fun initListener() {
        tv_note_content.addTextChangedListener(object : SimpleTextWatcherListener() {
            override fun afterTextChanged(s: Editable?) {

            }
        })

        iv_star.setOnClickListener {
            mViewModel.loadData()
        }
    }


}