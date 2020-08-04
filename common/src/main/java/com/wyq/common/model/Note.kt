package com.wyq.common.model

interface Note {

    interface Factory {
        fun build(): NoteConfig
    }
}