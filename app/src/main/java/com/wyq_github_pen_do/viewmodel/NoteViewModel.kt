package com.wyq_github_pen_do.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.TimeUtils
import com.wyq.common.base.BaseViewModel

/**
 * 编辑便签
 */
class NoteViewModel : BaseViewModel() {

    private var _hasEditNote = MutableLiveData<Boolean>()
    val hasEditNote: LiveData<Boolean> = _hasEditNote

    //日期
    private var _noteTitle = MutableLiveData<String>()
    val noteTitle: LiveData<String> = _noteTitle


    init {
        _noteTitle.postValue(TimeUtils.getNowString())
    }


    fun setHasEditNote(hasEditNote: Boolean){
        _hasEditNote.postValue(hasEditNote)
    }

}