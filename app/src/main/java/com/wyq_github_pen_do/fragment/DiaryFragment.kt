package com.wyq_github_pen_do.fragment

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SizeUtils
import com.wyq.common.base.BaseFragment
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.value
import com.wyq.common.model.*
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.adapter.NoteListAdapter
import com.wyq_github_pen_do.coroutine.NoteDetailScopedService
import com.wyq_github_pen_do.databinding.FragmentDiaryBinding
import com.wyq_github_pen_do.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class DiaryFragment : BaseFragment<FragmentDiaryBinding>(), INoteFragment {

    companion object {
        const val TAG = "DiaryFragment"
        fun newInstance() = DiaryFragment()
    }

    private val mViewModel by viewModel<NoteListViewModel>()
    private lateinit var noteListAdapter: NoteListAdapter

    override fun layoutId(): Int = R.layout.fragment_diary

    override fun initView() {
        recycler_main.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = SizeUtils.dp2px(20f)
            }
        })
        recycler_main.layoutManager = LinearLayoutManager(context)
        noteListAdapter = NoteListAdapter(this)
        recycler_main.adapter = noteListAdapter
    }


    override fun initData() {
        lifecycleScope.launch {
            mViewModel.getNoteList(NoteTypeEnum.diaryStyle()).collectLatest {
                noteListAdapter.submitData(lifecycle, it)
            }
        }

        noteListAdapter.addLoadStateListener {
            Timber.tag(TAG).d("addLoadStateListener: ")
        }
    }

    override fun initListener() {
    }

    override fun insertLatestNote(note: NoteListBean) {
        recycler_main.postDelayed({
            (recycler_main.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                0,
                SizeUtils.dp2px(60f)
            )
        },100)
    }

    override fun OnItemClickListener(note: NoteListBean) {
        val factory: Note.Factory =
            NotePreviewFactory(
                note,
                MainTabEnum.TAB_DIARY.index
            )
        lifecycleScope.launch {
            NoteDetailScopedService.startWithCoroutine(factory.build())
        }
    }


}