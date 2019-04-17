package br.com.pedrohmv

sealed class Event

object LoadingEvent : Event()

data class SuccessEvent<T>(val value: T) : Event()

data class ErrorEvent(val error: Throwable) : Event()