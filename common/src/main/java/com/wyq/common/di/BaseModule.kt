package com.wyq.common.di

import androidx.room.Room
import com.wyq.common.database.RoomDb
import org.koin.dsl.module

object BaseModule {

    val loadFeature by lazy {
        listOf(databaseModule)
    }


    private val databaseModule = module {
        single {
            Room.databaseBuilder(get(), RoomDb::class.java, "note_database.db")
                .fallbackToDestructiveMigration().build()
        }

        single { (get() as RoomDb).noteDao() }
    }
}