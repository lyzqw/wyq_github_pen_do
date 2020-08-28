package com.wyq.common.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 3,
    exportSchema = true
)
abstract class RoomDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}