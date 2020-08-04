package com.wyq_github_pen_do.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wyq.common.base.BaseViewModel

/**
 * 编辑便签
 */
class NoteViewModel : BaseViewModel() {

    private var _hasEditNote = MutableLiveData<Boolean>()
    val hasEditNote: LiveData<Boolean> = _hasEditNote


    fun setHasEditNote(hasEditNote: Boolean){
        _hasEditNote.postValue(hasEditNote)
    }

}