package com.wyq.common.mapper

interface Mapper<I, O> {

    fun map(input: I): O
}