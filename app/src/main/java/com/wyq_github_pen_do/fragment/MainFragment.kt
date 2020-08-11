package com.wyq_github_pen_do.fragment

import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseFragment
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.MAIN_BOTTOM_CORNER_SIZE
import com.wyq.common.ext.getShapeDrawable
import com.wyq.common.model.DefaultNoteConfig
import com.wyq.common.model.Note
import com.wyq.common.model.NoteFactory
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.activity.NoteActivity
import com.wyq_github_pen_do.adapter.NoteListAdapter
import com.wyq_github_pen_do.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.include_main_layer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mNoteDao by inject<NoteDao>()
    private lateinit var noteListAdapter: NoteListAdapter

    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView() {
        new_note.setOnClickListener {
            val factory: Note.Factory =
                NoteFactory(UUID.randomUUID().toString(), DefaultNoteConfig.EditMode.EDIT_AUTO_CAN)
            NoteActivity.start(factory.build())
        }
        recycler_main.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = SizeUtils.dp2px(20f)
            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                Log.d("main_recyc", "onDraw: "+parent.measuredHeight)
            }
        })
        recycler_main.layoutManager = LinearLayoutManager(context)
        noteListAdapter = NoteListAdapter()
        recycler_main.adapter = noteListAdapter

        setTitleToday()
    }

    private fun setTitleToday() {
        val date = TimeUtils.millis2String(
            System.currentTimeMillis(),
            TimeUtils.getSafeDateFormat("MM/dd")
        )
        tv_main_today.text = date + TimeUtils.getChineseWeek(System.currentTimeMillis())
        tv_main_today.background =
            getShapeDrawable(30f, ColorUtils.getColor(R.color.gray_999999))
    }

    override fun initData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val dataList = mNoteDao.findAll()
            Log.d("mainFragment", "initData: " + dataList)
            noteListAdapter.setNewData(dataList.map { createNoteListBean(it) }.toMutableList())
        }
    }

    private fun createNoteListBean(entity: NoteEntity) =
        NoteListBean(
            NoteTypeEnum.TYPE_DIARY_STYLE_1.code,
            entity.noteId,
            entity.title,
            entity.content,
            entity.create_date,
            entity.tag_color,
            entity.tag_content,
            entity.note_image
        )

    override fun initListener() {
    }


}