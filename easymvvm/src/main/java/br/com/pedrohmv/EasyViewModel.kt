package br.com.pedrohmv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext

abstract class EasyViewModel(private val appCoroutineScope: AppCoroutineScope) : ViewModel() {

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit, errorFunction: ErrorFunction) {
        appCoroutineScope.launch(block = block, errorFunction = errorFunction)
    }

    fun launchOnIO(block: suspend CoroutineScope.() -> Unit, errorFunction: ErrorFunction) {
        appCoroutineScope.launch(appCoroutineScope.job + Dispatchers.IO, block, errorFunction)
    }

    suspend fun <T> updateValue(mutableLiveData: MutableLiveData<T>, value: T) {
        withContext(appCoroutineScope.coroutineDispatcher) {
            mutableLiveData.value = value
        }
    }


    override fun onCleared() {
        super.onCleared()
        appCoroutineScope.cancel()
    }

}