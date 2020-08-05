package com.wyq_github_pen_do.fragment

import com.wyq.common.base.BaseFragment
import com.wyq.common.ext.value
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.activity.NoteActivity.Companion.AUTO_EDIT_NOTE
import com.wyq_github_pen_do.databinding.FragmentMainBinding
import com.wyq_github_pen_do.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * 编辑便签
 */
class NoteFragment : BaseFragment<FragmentMainBinding>() {

    private val mViewModel by sharedViewModel<NoteViewModel>()

    override fun layoutId(): Int = R.layout.fragment_note

    override fun initView() {

    }

    override fun initData() {
        mViewModel.setHasEditNote(activity?.intent?.getBooleanExtra(AUTO_EDIT_NOTE, true).value())
    }


}