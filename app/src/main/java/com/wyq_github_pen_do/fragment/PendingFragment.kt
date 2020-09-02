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
import com.wyq.common.model.Note
import com.wyq.common.model.NoteListBean
import com.wyq.common.model.NotePreviewFactory
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.adapter.NoteListAdapter
import com.wyq_github_pen_do.coroutine.NoteDetailScopedService
import com.wyq_github_pen_do.databinding.FragmentNoteDetailBinding
import com.wyq_github_pen_do.databinding.FragmentPendingBinding
import com.wyq_github_pen_do.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.android.synthetic.main.fragment_pending.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PendingFragment : BaseFragment<FragmentPendingBinding>(), INoteFragment {


    companion object {
        const val TAG = "PendingFragment"
        fun newInstance() = PendingFragment()
    }

    private val mViewModel by viewModel<NoteListViewModel>()
    private lateinit var noteListAdapter: NoteListAdapter

    override fun layoutId(): Int = R.layout.fragment_pending

    override fun initView() {
        Log.d(TAG, "initView: ")
        binding.recyclerMainPending.apply {
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = SizeUtils.dp2px(20f)
                }
            })
            layoutManager = LinearLayoutManager(context)
            noteListAdapter = NoteListAdapter(this@PendingFragment)
            adapter = noteListAdapter
        }
    }


    override fun initData() {
        Log.d(TAG, "initData: ")
        lifecycleScope.launch {
            val typeList = NoteTypeEnum.pendingStyle()
            Log.d(TAG, "initData: "+typeList)
            mViewModel.getNoteList(typeList).collectLatest {
                Log.d(TAG, "拿到数据: "+it)
                noteListAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun initListener() {
    }

    override fun insertLatestNote(note: NoteListBean) {
        recycler_main_pending.postDelayed({
            (recycler_main_pending.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                0,
                SizeUtils.dp2px(60f)
            )
        }, 100)
    }

    override fun OnItemClickListener(note: NoteListBean) {
        val factory: Note.Factory =
            NotePreviewFactory(
                note,
                MainTabEnum.TAB_PENDING.index
            )
        lifecycleScope.launch {
            NoteDetailScopedService.startWithCoroutine(factory.build())
        }
    }


}