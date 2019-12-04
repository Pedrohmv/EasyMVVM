package br.com.pedrohmv

import kotlinx.coroutines.CoroutineDispatcher

class TestCoroutineScope(coroutineDispatcher: CoroutineDispatcher) :
    AppCoroutineScope(coroutineDispatcher) {

    override val coroutineContext = job + coroutineDispatcher

}
