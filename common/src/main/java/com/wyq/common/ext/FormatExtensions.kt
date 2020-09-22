package com.wyq.common.ext


fun Boolean?.value() = this == true

fun String?.value() = this.orEmpty()

fun Long?.value() = this ?: 0

fun Float?.value() = this ?: 0F

fun Int?.value() = this ?: 0