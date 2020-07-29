package com.wyq_github_pen_do.activity

import android.view.View
import com.wyq.common.base.BaseActivity
import com.wyq.common.database.NoteDao
import com.wyq.common.database.NoteEntity
import com.wyq.common.ext.isMainThread
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val dao by inject<NoteDao>()

    override val layoutId: Int = R.layout.activity_main

    fun test(view: View) {
        Timber.d("onclick. dao: ${dao}")


        GlobalScope.launch {
            dao.insert(NoteEntity(1, "第一个note"))
            Timber.d("onclick. insert")
            val notes = dao.findAll()
            Timber.d("onclick. query1: ${notes}")
            dao.insert(NoteEntity(1, "第二个note, 使用同一个id"))
            Timber.d("onclick. query2: ${dao.findAll()}")
        }

    }

    fun delete(view: View) {
        GlobalScope.launch {
            Timber.d("delete. 先查一下: ${dao.findAll()}")
            dao.delete(NoteEntity(1))
            Timber.d("delete. 删除后,先查一下: ${dao.findAll()}")
        }
    }


}