package com.wyq.common.ext


fun Boolean?.value() = this == true

fun String?.value() = this.orEmpty()