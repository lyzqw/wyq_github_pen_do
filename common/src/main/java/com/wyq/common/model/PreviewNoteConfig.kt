package com.wyq.common.model

import com.wyq.common.ext.value

/**
 * 打开笔记默认配置
 */
data class PreviewNoteConfig(
    val note: NoteListBean,
    val mainIndex: Int
) : NoteConfig {

    override fun noteId(): String = note.noteId.value()

    override fun autoEditNote() = false

    override fun mainIndex() = mainIndex


    override fun getPreviewNote(): NoteListBean? = note
}
