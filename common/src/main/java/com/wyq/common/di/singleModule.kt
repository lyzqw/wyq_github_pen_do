package com.wyq.common.di

import com.wyq.common.mapper.Entity2NoteListMapper
import org.koin.dsl.module


val singleModule = module {
    single { Entity2NoteListMapper()}
}