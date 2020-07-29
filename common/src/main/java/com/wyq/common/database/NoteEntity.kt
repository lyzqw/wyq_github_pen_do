package com.wyq.common.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = ""
)