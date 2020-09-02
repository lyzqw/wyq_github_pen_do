package com.wyq.common.enum

import com.wyq.common.ext.throwExceptionWhenDebug

/**
 * 笔记的类型
 */
enum class NoteTypeEnum(val code: Int) {
    /**
     * 日记
     */
    TYPE_DIARY_STYLE_1(1), //左方向
    TYPE_DIARY_STYLE_2(2), //右方向

    /**
     * 笔记
     */
    TYPE_NOTE_STYLE_1(3),
    TYPE_NOTE_STYLE_2(4),

    /**
     * 待办
     */
    TYPE_PENDING_STYLE_1(5),
    TYPE_PENDING_STYLE_2(6),

    /**
     * 日程
     */
    TYPE_SCHEDULE_STYLE_1(7),
    TYPE_SCHEDULE_STYLE_2(8);

    companion object {

        fun get(code: Int?): NoteTypeEnum {
            val type = values().find { it.code == code }
            type ?: throwExceptionWhenDebug("参数错误")
            return type ?: TYPE_DIARY_STYLE_1
        }

        fun diaryStyle(): List<Int> = arrayListOf(TYPE_DIARY_STYLE_1.code, TYPE_DIARY_STYLE_2.code)

        fun noteStyle(): List<Int> = arrayListOf(TYPE_NOTE_STYLE_1.code, TYPE_NOTE_STYLE_2.code)

        fun pendingStyle(): List<Int> = arrayListOf(TYPE_PENDING_STYLE_1.code, TYPE_PENDING_STYLE_2.code)

        fun scheduleStyle(): List<Int> = arrayListOf(TYPE_SCHEDULE_STYLE_1.code, TYPE_SCHEDULE_STYLE_2.code)

        fun allStyle(): List<Int> = values().map { it.code }.toList()
    }
}