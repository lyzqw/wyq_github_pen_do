package com.wyq_github_pen_do.fragment

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.wyq.common.base.BaseFragment
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.activity.NoteActivity
import com.wyq_github_pen_do.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView() {
        new_note.setOnClickListener {
            ActivityUtils.startActivity(NoteActivity::class.java)
        }
    }

    override fun initData() {

    }


}