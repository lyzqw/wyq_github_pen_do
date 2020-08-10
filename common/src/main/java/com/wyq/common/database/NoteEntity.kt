package com.wyq.common.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    /**
     * 默认表自增id
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    /**
     * 笔记的类型 {@link com.wyq.common.enum.NoteTypeEnum}
     */
    var type: Int,
    /**
     * 笔记唯一标识
     */
    @ColumnInfo(name = "note_id")
    var noteId: String? = "",

    /**
     * 笔记title
     */
    @ColumnInfo(name = "title")
    var title: String? = "",

    /**
     * 笔记content
     */
    @ColumnInfo(name = "content")
    var content: String? = "",

    /**
     * 创建时间
     */
    var create_date: String? = "",

    /**
     * 标签颜色
     */
    var tag_color: Int? = 0,

    /**
     * 标签文字
     */
    var tag_content: String? = "",

    /**
     * 笔记图片
     */
    var note_image: String? = ""


)