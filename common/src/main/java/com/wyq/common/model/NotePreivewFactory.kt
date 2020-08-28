package com.wyq.common.model

class NoteNormalFactory(
    private val noteId: String,
    private val editMode: DefaultNoteConfig.EditMode,
    private val mainIndex: Int
) : Note.Factory {


    override fun build(): NoteConfig = DefaultNoteConfig(noteId, editMode,mainIndex)
}