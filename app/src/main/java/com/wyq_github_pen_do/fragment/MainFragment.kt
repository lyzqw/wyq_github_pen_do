package com.wyq_github_pen_do.fragment

import androidx.lifecycle.lifecycleScope
import com.wyq.common.base.BaseFragment
import com.wyq.common.database.NoteDao
import com.wyq.common.model.DefaultNoteConfig
import com.wyq.common.model.Note
import com.wyq.common.model.NoteFactory
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.activity.NoteActivity
import com.wyq_github_pen_do.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.inject
import java.util.*


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mNoteDao by inject<NoteDao>()


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

    override fun initListener() {

        query.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                println(mNoteDao.findAll())
            }
        }
    }


}