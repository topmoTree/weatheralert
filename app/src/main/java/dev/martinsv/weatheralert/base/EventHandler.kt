package dev.martinsv.weatheralert.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}