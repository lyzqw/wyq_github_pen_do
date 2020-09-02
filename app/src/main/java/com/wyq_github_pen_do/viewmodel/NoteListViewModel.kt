package com.wyq_github_pen_do.viewmodel

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.wyq.common.base.BaseViewModel
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.NoteTypeEnum
import com.wyq.common.mapper.Mapper
import com.wyq.common.model.NoteListBean
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * 便签列表
 */
class NoteListViewModel(private val mNoteEntity2NoteListMapper: Mapper<NoteEntity, NoteListBean>) :
    BaseViewModel(), KoinComponent {


    private val mNoteDao by inject<NoteDao>()

    private val config = PagingConfig(
        initialLoadSize = 10,
        pageSize = 10,
        prefetchDistance = 1,
        enablePlaceholders = true,
        maxSize = 200 * 10000
    )

    fun getNoteList(typeList: List<Int>) =
        Pager(config) {
            Log.d("wqq", "getNoteList: "+typeList)
            mNoteDao.findNoteList(typeList)
        }.flow.map { pagingData: PagingData<NoteEntity> ->
            pagingData.map { mNoteEntity2NoteListMapper.map(it) }
        }
}


