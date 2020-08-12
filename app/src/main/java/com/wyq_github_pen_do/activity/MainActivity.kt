package com.wyq_github_pen_do.activity

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseActivity
import com.wyq.common.database.NoteDao
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.ext.clickJitter
import com.wyq.common.ext.getShapeDrawable
import com.wyq.common.model.DefaultNoteConfig
import com.wyq.common.model.Note
import com.wyq.common.model.NoteFactory
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivityMainBinding
import com.wyq_github_pen_do.fragment.DiaryFragment
import com.wyq_github_pen_do.fragment.NoteFragment
import com.wyq_github_pen_do.fragment.PendingFragment
import com.wyq_github_pen_do.fragment.ScheduleFragment
import kotlinx.android.synthetic.main.include_main_layer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

/**
 * 主页
 *
 * todo  换肤,
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mNoteData: NoteDao by inject<NoteDao>()

    private lateinit var fragments: List<Fragment>

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {
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
            0
        )
    }

    override fun loadData() {
        setTitleToday()
    }

    override fun initListener() {
        binding.run {
            layoutMainLayer.mainBottomLayout.setOnDiaryClickListener { isSelected ->
                FragmentUtils.showHide(MainTabEnum.TAB_DIARY.index, fragments)
            }

            layoutMainLayer.mainBottomLayout.setOnNoteClickListener { isSelected ->
                FragmentUtils.showHide(MainTabEnum.TAB_NOTE.index, fragments)
            }
            layoutMainLayer.mainBottomLayout.setOnPendingClickListener { isSelected ->
                FragmentUtils.showHide(MainTabEnum.TAB_PENDING.index, fragments)
            }
            layoutMainLayer.mainBottomLayout.setOnScheduleClickListener { isSelected ->
                FragmentUtils.showHide(MainTabEnum.TAB_SCHEDULE.index, fragments)
            }
        }

        new_note.setOnClickListener {
            val factory: Note.Factory =
                NoteFactory(UUID.randomUUID().toString(), DefaultNoteConfig.EditMode.EDIT_AUTO_CAN)
            NoteDetailActivity.start(factory.build())
        }

        image_main_search.clickJitter {
            lifecycleScope.launch(Dispatchers.IO) {
                mNoteData.updateNote(0, "修改后的....")
                Log.d("wqq", "initView: ")
            }
        }
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