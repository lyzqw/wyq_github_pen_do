package com.wyq.common.model

import com.wyq.common.database.NoteEntity
import com.wyq.common.enum.NoteTypeEnum

data class NoteListBean(

    /**
     * itemType
     */
    var type: Int,

    /**
     * 笔记唯一标识
     */
    var noteId: String? = "",


    /**
     * 笔记title
     */
    var title: String? = "",
    /**
     * 笔记content
     */
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

) : BaseNoteListBean() {

    companion object {
        fun createNoteListBean(entity: NoteEntity) =
            NoteListBean(
                NoteTypeEnum.TYPE_DIARY_STYLE_1.code,
                entity.noteId,
                entity.title,
                entity.content,
                entity.create_date,
                entity.tag_color,
                entity.tag_content,
                entity.note_image
            )

    }


}