package br.com.pedrohmv

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineScope : AppCoroutineScope() {

    override val coroutineContext = job + Dispatchers.Unconfined

}
