package com.clcmo.core.presentation.helpers.livedata

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? = when {
        hasBeenHandled -> null
        else -> {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

fun <T> T.toEvent() = Event(this)