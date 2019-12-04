package br.com.pedrohmv

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class AppCoroutineScope(val coroutineDispatcher: CoroutineDispatcher) : CoroutineScope {

    val job = Job()
    override val coroutineContext = job + coroutineDispatcher

    fun launch(
        coroutineCtx: CoroutineContext = coroutineContext,
        block: suspend CoroutineScope.() -> Unit,
        errorFunction: ErrorFunction
    ) {
        launch(coroutineCtx) {
            try {
                block()
            } catch (error: Throwable) {
                errorFunction(error)
            }
        }
    }

}

typealias ErrorFunction = suspend (Throwable) -> Unit