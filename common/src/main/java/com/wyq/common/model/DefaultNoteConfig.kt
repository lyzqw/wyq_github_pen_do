package com.wyq.common.model

/**
 * 打开笔记默认配置
 */
data class DefaultNoteConfig(val noteId: String, val editMode: EditMode) : NoteConfig {


    override fun noteId(): String  = noteId


    override fun autoEditNote() = editMode == EditMode.EDIT_AUTO_CAN


    enum class EditMode(val code: Int) {
        /**
         * 默认不可直接编辑
         */
        EDIT_NORMAL(1),

        /**
         * 直接可以编辑笔记
         */
        EDIT_AUTO_CAN(2);
    }
}
