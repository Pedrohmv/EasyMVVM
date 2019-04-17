package br.com.pedrohmv

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AppCoroutineScope : CoroutineScope {

    val job = Job()
    override val coroutineContext = job + Dispatchers.Main

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

typealias ErrorFunction = (Throwable) -> Unit