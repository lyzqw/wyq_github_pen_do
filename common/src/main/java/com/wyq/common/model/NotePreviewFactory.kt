package com.wyq.common.model

class NotePreviewFactory(
    private val note: NoteListBean,
    private val mainIndex: Int
) : Note.Factory {


    override fun build(): NoteConfig = PreviewNoteConfig(note, mainIndex)
}