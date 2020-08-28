package com.wyq.common.mapper

import com.wyq.common.database.NoteEntity
import com.wyq.common.ext.value
import com.wyq.common.model.NoteListBean

class Entity2NoteListMapper : Mapper<NoteEntity, NoteListBean> {


    private fun createNoteListBean(entity: NoteEntity) =
        NoteListBean(
            entity.type.value(),
            entity.noteId,
            entity.title,
            entity.content,
            entity.create_date,
            entity.tag_color,
            entity.tag_content,
            entity.note_image
        )

    override fun map(input: NoteEntity): NoteListBean {
        return createNoteListBean(input)
    }

}