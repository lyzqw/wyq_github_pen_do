package com.wyq_github_pen_do.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseActivity
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.getShapeDrawable
import com.wyq.common.ext.value
import com.wyq.common.model.*
import com.wyq_github_pen_do.Listener.INoteFragment
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.coroutine.NoteDetailScopedService
import com.wyq_github_pen_do.databinding.ActivityMainBinding
import com.wyq_github_pen_do.fragment.DiaryFragment
import com.wyq_github_pen_do.fragment.NoteFragment
import com.wyq_github_pen_do.fragment.PendingFragment
import com.wyq_github_pen_do.fragment.ScheduleFragment
import com.wyq_github_pen_do.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.include_main_layer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * 主页
 *
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var fragments: List<Fragment>

    private val mMainViewModel by viewModel<MainViewModel>()
    private val mNoteDao by inject<NoteDao>()

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {
        binding.layoutMainLayer.viewModel = mMainViewModel
        loadFragments()
    }

    private fun loadFragments() {
        fragments =
            mutableListOf(
                DiaryFragment.newInstance(),
                NoteFragment.newInstance(),
                PendingFragment.newInstance(),
                ScheduleFragment.newInstance()
            )
        FragmentUtils.add(
            supportFragmentManager,
            fragments,
            R.id.main_container,
            MainTabEnum.TAB_DIARY.index
        )
        mMainViewModel.updateMainTabEnum(MainTabEnum.TAB_DIARY)
    }

    override fun loadData() {
        setTitleToday()
    }

    override fun initListener() {
        binding.run {
            layoutMainLayer.mainBottomLayout.setOnDiaryClickListener { isSelected ->
                updateSelectedMainTab(MainTabEnum.TAB_DIARY, isSelected)
            }

            layoutMainLayer.mainBottomLayout.setOnNoteClickListener { isSelected ->
                updateSelectedMainTab(MainTabEnum.TAB_NOTE, isSelected)
            }
            layoutMainLayer.mainBottomLayout.setOnPendingClickListener { isSelected ->
                updateSelectedMainTab(MainTabEnum.TAB_PENDING, isSelected)
            }
            layoutMainLayer.mainBottomLayout.setOnScheduleClickListener { isSelected ->
                updateSelectedMainTab(MainTabEnum.TAB_SCHEDULE, isSelected)
            }
        }

        new_note.setOnClickListener {
            lifecycleScope.launch {
                val factory: Note.Factory =
                    NoteNormalFactory(
                        UUID.randomUUID().toString(),
                        DefaultNoteConfig.EditMode.EDIT_AUTO_CAN,
                        mMainViewModel.getMainTabIndex()
                    )
                val result = NoteDetailScopedService.startWithCoroutine(factory.build())
                if (result) {
                    lifecycleScope.launch {
                        delay(1000)
                        insertLatestNote()
                    }
                }
            }
        }

        image_main_search.clickJitter {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    for (i in 0..20) {
                        mNoteDao.insert(createNoteEntity())
                    }
                }
            }
        }

        iv_calender.clickJitter {}
    }

    private fun createNoteEntity(): NoteEntity {
        val noteEntity = NoteEntity(
            type = NoteTypeEnum.TYPE_DIARY_STYLE_1.code,
            noteId = UUID.randomUUID().toString(),
            title = Random().nextInt(12312).toString(),
            create_date = System.currentTimeMillis().toString(),
            content = ""
        )
        return noteEntity
    }

    private suspend fun insertLatestNote() {
        val note = withContext(Dispatchers.IO) {
            mMainViewModel.getLatestNote()?.let { NoteListBean.createNoteListBean(it) }
        }
        if (note != null) {
            val iNoteFragment =
                fragments[mMainViewModel.mainTabIndex.value.value()] as INoteFragment
            iNoteFragment.insertLatestNote(note)
        }
    }

    private fun updateSelectedMainTab(
        mainTabEnum: MainTabEnum,
        isSelected: Boolean
    ) {
        FragmentUtils.showHide(mainTabEnum.index, fragments)
        mMainViewModel.updateMainTabEnum(mainTabEnum)
        mMainViewModel.updateAddNoteColor(if (isSelected) mainTabEnum.selectedColor else mainTabEnum.normalColor)
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


}