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
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 * 主页
 */
class MainViewModel : BaseViewModel(), KoinComponent {

    private var _noteDate = MutableLiveData<String>()
    val noteDate: LiveData<String> = _noteDate


    init {
    }





}