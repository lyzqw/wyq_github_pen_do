package com.wyq_github_pen_do.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.util.LogTime
import com.wyq.common.base.BaseViewModel
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.NoteTypeEnum
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 * 编辑便签
 */
class NoteViewModel : BaseViewModel(), KoinComponent {

    private val mNoteDao by inject<NoteDao>()

    private var _hasEditNote = MutableLiveData<Boolean>()
    val hasEditNote: LiveData<Boolean> = _hasEditNote

    //日期
    private var _noteDate = MutableLiveData<String>()
    val noteDate: LiveData<String> = _noteDate

    val noteTitle = MutableLiveData<String>()
    val noteContent = MutableLiveData<String>()

    init {
        _noteDate.postValue(TimeUtils.getNowString())
    }


    fun setHasEditNote(hasEditNote: Boolean) {
        if(hasEditNote){
            
        }
        //todo 取出正确的时间
        _hasEditNote.postValue(hasEditNote)
    }

    fun saveNoteWhenFinish(noteId: String) {
        if (hasEditNote.value == true && hasEditContent()) {
            viewModelScope.launch(Dispatchers.IO) {
                val start = LogTime.getLogTime()
                val noteEntity = NoteEntity(
                    type_note = NoteTypeEnum.TYPE_DIARY_STYLE_1.code,
                    noteId = noteId,
                    title = noteTitle.value,
                    create_date = System.currentTimeMillis().toString(),
                    content = noteContent.value
                )
                mNoteDao.insert(noteEntity)
                Timber.d("===saveNoteWhenFinish.耗时:  ${LogTime.getElapsedMillis(start)}")
            }
        }
        Timber.d("===saveNoteWhenFinish:  finish")
    }

    private fun hasEditContent(): Boolean =
        noteTitle.value?.isNotEmpty() == true || noteContent.value?.isNotEmpty() == true

    fun loadData() {

    }

}