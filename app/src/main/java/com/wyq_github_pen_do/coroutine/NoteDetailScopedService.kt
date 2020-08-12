package com.wyq_github_pen_do.coroutine

import android.util.Log
import com.wyq.common.model.NoteConfig
import com.wyq_github_pen_do.activity.NoteDetailActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation

object NoteDetailScopedService {

    private lateinit var continuation: Continuation<Boolean>

    suspend fun startWithCoroutine(config: NoteConfig): Boolean {
        try {
            val result = suspendCancellableCoroutine<Boolean> {
                continuation = it
                NoteDetailActivity.start(config)
            }
            return result
        } catch (e: Throwable) {
            Log.d("note_detail", "startWithCoroutine: " + e.message)
        }
        return false
    }


    fun getContinuation(): Continuation<Boolean>? {
        return if (::continuation.isInitialized) {
            continuation
        } else {
            null
        }

    }
}