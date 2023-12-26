package com.example.composecleanapparchitecture.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

// class that handles sending event to the UI
// this event can be showing toast message, SnackBar
object EventBus {

    private val _events = Channel<Any>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any){
        _events.send(event)
    }
}

// types of event supported by app
sealed interface Event{
    data class Toast(val message: String): Event
    data class SnackBar(val message: String): Event
    data class Dialog(val title: String, val text: String): Event

}