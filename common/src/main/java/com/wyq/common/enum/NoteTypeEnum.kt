package com.wyq.common.enum

import com.wyq.common.ext.throwExceptionWhenDebug

/**
 * 笔记的类型
 */
enum class NoteTypeEnum(val code: Int) {
    /**
     * 日记
     */
    TYPE_DIARY(1),

    /**
     * 笔记
     */
    TYPE_NOTE(2),

    /**
     * 待办
     */
    TYPE_PENDING(3),

    /**
     * 日程
     */
    TYPE_SCHEDULE(4);

    companion object {
        fun get(code: Int?): NoteTypeEnum {
            val type = values().find { it.code == code }
            type ?: throwExceptionWhenDebug("参数错误")
            return type ?: TYPE_DIARY
        }
    }
}