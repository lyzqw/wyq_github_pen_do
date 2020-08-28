package com.wyq_github_pen_do.Listener

import com.wyq.common.model.NoteListBean

interface INoteFragment {

    fun insertLatestNote(note: NoteListBean)

    fun OnItemClickListener(note: NoteListBean)
}