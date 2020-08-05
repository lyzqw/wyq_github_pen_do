package com.wyq.common.model

class NoteFactory(private val noteId: String, private val editMode: DefaultNoteConfig.EditMode) : Note.Factory {


    override fun build(): NoteConfig = DefaultNoteConfig(noteId, editMode)
}