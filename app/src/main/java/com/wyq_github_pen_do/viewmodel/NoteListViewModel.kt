package com.wyq_github_pen_do.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wyq.common.base.BaseViewModel
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.mapper.Mapper
import com.wyq.common.model.NoteListBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * 便签列表
 */
class NoteListViewModel(private val mNoteEntity2NoteListMapper: Mapper<NoteEntity, NoteListBean>) :
    BaseViewModel(), KoinComponent {

    companion object {
        const val LOAD_SIZE = 15
    }

    private var pageSize: Int = 0
    private val mNoteDao by inject<NoteDao>()
    private val _noteList = MutableLiveData<List<NoteListBean>>()
    var noteList = _noteList

    fun loadNoteList(typeList: List<Int>) {
        viewModelScope.launch {
            pageSize = 0
            val result = withContext(Dispatchers.IO) {
                mNoteDao.findAll(typeList, pageSize, LOAD_SIZE).map {
                    mNoteEntity2NoteListMapper.map(it)
                }
            }

            _noteList.value = result
        }
    }

    fun loadMoreNoteList(typeList: List<Int>) {
        viewModelScope.launch {
            pageSize++
            val result = withContext(Dispatchers.IO) {
                Log.d("DiaryFragment", "loadMoreNoteList: "+pageSize * LOAD_SIZE +" ==== pageSize: "+pageSize)
                mNoteDao.findAll(typeList, pageSize * LOAD_SIZE, LOAD_SIZE).map {
                    mNoteEntity2NoteListMapper.map(it)
                }
            }
            _noteList.value = result
        }
    }

    fun isRefresh() = pageSize == 0

}


