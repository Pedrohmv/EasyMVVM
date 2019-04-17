package br.com.pedrohmv

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.updateValue(value: T) {
    withContext(Dispatchers.Main) {
        this@updateValue.value = value
    }
}

fun <T> LifecycleOwner.easyObserve(liveData: MutableLiveData<out T>, function: (T) -> Unit) {
    liveData.observe(this, Observer { data -> data?.let { function(data) } })
}
