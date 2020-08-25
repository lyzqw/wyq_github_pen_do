package com.wyq_github_pen_do.activity

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseActivity
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.getShapeDrawable
import com.wyq.common.model.DefaultNoteConfig
import com.wyq.common.model.Note
import com.wyq.common.model.NoteFactory
import com.wyq.common.model.NoteListBean
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.coroutine.NoteDetailScopedService
import com.wyq_github_pen_do.databinding.ActivityMainBinding
import com.wyq_github_pen_do.fragment.DiaryFragment
import com.wyq_github_pen_do.fragment.NoteFragment
import com.wyq_github_pen_do.fragment.PendingFragment
import com.wyq_github_pen_do.fragment.ScheduleFragment
import com.wyq_github_pen_do.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.include_main_layer.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * 主页
 *
 * todo  换肤,
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var fragments: List<Fragment>

    private val mMainViewModel by viewModel<MainViewModel>()

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
                    NoteFactory(
                        UUID.randomUUID().toString(),
                        DefaultNoteConfig.EditMode.EDIT_AUTO_CAN
                    )
                val result = NoteDetailScopedService.startWithCoroutine(factory.build())
                if (result) {
                    val note = mMainViewModel.getLatestNote()?.also { NoteListBean.createNoteListBean(it) }
                    //fragments[mMainViewModel.mainTabIndex.value.value()].insertLatestNote(note) 接口调用
                }
                Log.d("note_detail", "result: " + result)
            }
        }

        image_main_search.clickJitter {}
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