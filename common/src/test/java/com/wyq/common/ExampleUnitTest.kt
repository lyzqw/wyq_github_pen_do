package com.wyq.common

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        for (line in 0 until 1) {
            println(line)
        }
    }


    @InternalCoroutinesApi
    @Test
    fun test_conflate() = runBlocking {
        flow {
            List(100) {
                emit(it)
            }
        }.conflate()
            .collect { value ->
                println("Collecting $value")
                delay(100)
                println("$value collected")
            }
    }

    @InternalCoroutinesApi
    @Test
    fun test_collectLatest() = runBlocking {
        flow {
            List(100) {
                emit(it)
            }
        }.collectLatest { value ->
            println("Collecting $value")
                delay(100)
            println("$value collected")
        }
    }
}