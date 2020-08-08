package com.wyq.common.model

data class NoteListBean(

    /**
     * itemType
     */
    var itemType: Int,

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

) : BaseNoteListBean()