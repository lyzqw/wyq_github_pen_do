package com.wyq_github_pen_do

import androidx.room.Room
import com.wyq.common.database.RoomDb
import com.wyq.common.di.singleModule
import com.wyq.common.mapper.Entity2NoteListMapper
import com.wyq_github_pen_do.viewmodel.MainViewModel
import com.wyq_github_pen_do.viewmodel.NoteListViewModel
import com.wyq_github_pen_do.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val loadFeature by lazy {
        listOf(databaseModule, appViewModule, singleModule)
    }

    private val appViewModule = module {
        viewModel { NoteViewModel() }
        viewModel { MainViewModel() }
        viewModel { NoteListViewModel(get<Entity2NoteListMapper>()) }
    }

    private val databaseModule = module {
        single {
            Room.databaseBuilder(get(), RoomDb::class.java, "note_database.db")
                .fallbackToDestructiveMigration().build()
        }

        single { (get() as RoomDb).noteDao() }
    }

}