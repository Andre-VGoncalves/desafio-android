package com.picpay.desafio.android.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {
    private val job = Job()
    override var coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        job.cancel()
    }
}