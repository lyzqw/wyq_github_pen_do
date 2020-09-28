package com.wyq_github_pen_do.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.util.LogTime
import com.wyq.common.base.BaseViewModel
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.MainTabEnum
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.ext.value
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

/**
 * 编辑便签
 */
class NoteViewModel : BaseViewModel(), KoinComponent {
    //日期
    private var _noteDate = MutableLiveData<String>()
    val noteDate: LiveData<String> = _noteDate

    //标题头
    var _noteTitle = MutableLiveData<String>()

    //编辑的内容
    var _noteContent = MutableLiveData<String>()

    private var _canAutoEdit = MutableLiveData<Boolean>()
    val canAutoEdit: LiveData<Boolean> = _canAutoEdit

    private val mNoteDao by inject<NoteDao>()

    fun setCanAutoEdit(canAutoEdit: Boolean) {
        _canAutoEdit.value = canAutoEdit
    }

    fun saveNoteWhenFinish(
        noteId: String,
        mainIndex: Int,
        continuation: Continuation<Boolean>?
    ) {
        val hasEdit = canAutoEdit.value == true && hasEditContent()
        if (hasEdit) {
            viewModelScope.launch(Dispatchers.IO) {
                val start = LogTime.getLogTime()
                val lastNoteEntity = mNoteDao.findLatestNote()
                val noteEntity = NoteEntity(
                    type = getNoteListType(lastNoteEntity, mainIndex),
                    noteId = noteId,
                    title = _noteTitle.value,
                    create_date = System.currentTimeMillis().toString(),
                    content = _noteContent.value
                )
                mNoteDao.insert(noteEntity)
                Timber.d("===saveNoteWhenFinish.耗时:  ${LogTime.getElapsedMillis(start)}")
            }
        }
        Timber.d("===saveNoteWhenFinish:  finish")
        continuation?.resume(hasEdit)
    }

    private fun getNoteListType(lastNoteEntity: NoteEntity?, mainIndex: Int): Int {
        when (MainTabEnum.get(mainIndex)) {
            MainTabEnum.TAB_DIARY -> {
                if (lastNoteEntity != null && NoteTypeEnum.get(lastNoteEntity.type) == NoteTypeEnum.TYPE_DIARY_STYLE_1) {
                    return NoteTypeEnum.TYPE_DIARY_STYLE_2.code
                }
                return NoteTypeEnum.TYPE_DIARY_STYLE_1.code
            }
            MainTabEnum.TAB_NOTE -> {
                if (lastNoteEntity != null && NoteTypeEnum.get(lastNoteEntity.type) == NoteTypeEnum.TYPE_NOTE_STYLE_1) {
                    return NoteTypeEnum.TYPE_NOTE_STYLE_2.code
                }
                return NoteTypeEnum.TYPE_NOTE_STYLE_1.code
            }

            MainTabEnum.TAB_PENDING -> {
                if (lastNoteEntity != null && NoteTypeEnum.get(lastNoteEntity.type) == NoteTypeEnum.TYPE_PENDING_STYLE_1) {
                    return NoteTypeEnum.TYPE_PENDING_STYLE_2.code
                }
                return NoteTypeEnum.TYPE_PENDING_STYLE_1.code
            }
            MainTabEnum.TAB_SCHEDULE -> {
                if (lastNoteEntity != null && NoteTypeEnum.get(lastNoteEntity.type) == NoteTypeEnum.TYPE_SCHEDULE_STYLE_1) {
                    return NoteTypeEnum.TYPE_SCHEDULE_STYLE_2.code
                }
                return NoteTypeEnum.TYPE_SCHEDULE_STYLE_1.code
            }
        }
    }

    private fun hasEditContent(): Boolean =
        _noteTitle.value?.isNotEmpty() == true || _noteContent.value?.isNotEmpty() == true

    fun setNoteTitle(title: String) {
        _noteTitle.value = title
    }

    fun setNoteContent(content: String) {
        _noteContent.value = content
    }

    fun setNoteTitleCreateDate(canAutoEdit: Boolean, date: String) {
        if (canAutoEdit) {
            _noteDate.value = TimeUtils.getNowString()
        } else {
            _noteDate.value = TimeUtils.millis2String(date.toLongOrNull().value())
        }
    }

    fun loadData() {

    }

}