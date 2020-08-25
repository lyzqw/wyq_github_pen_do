package com.wyq_github_pen_do.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wyq.common.base.BaseViewModel
import com.wyq.common.database.NoteDao
import com.wyq.common.enum.MainTabEnum
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * 主页
 */
class MainViewModel : BaseViewModel(), KoinComponent {

    private val mNoteData by inject<NoteDao>()


    private var _mainTabIndexLiveData = MutableLiveData<Int>()
    val mainTabIndex: LiveData<Int> = _mainTabIndexLiveData


    private var _addNoteColorLiveData = MutableLiveData<Int>()
    val addNoteColor: LiveData<Int> = _addNoteColorLiveData


    init {
        _addNoteColorLiveData.value = MainTabEnum.TAB_DIARY.normalColor
    }


    fun updateMainTabEnum(mainTabEnum: MainTabEnum) {
        _mainTabIndexLiveData.postValue(mainTabEnum.index)
    }

    fun updateAddNoteColor(color: Int) {
        _addNoteColorLiveData.value = color
    }

    fun getLatestNote() = mNoteData.findLatestNote()

}