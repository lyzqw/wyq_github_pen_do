package com.wyq.common.model

interface NoteConfig {
    fun noteId(): String
    fun autoEditNote(): Boolean
    fun mainIndex(): Int
}