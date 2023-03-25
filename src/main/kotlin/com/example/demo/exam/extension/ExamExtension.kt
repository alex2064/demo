package com.example.demo.exam.extension

import java.util.Optional

fun <T: Any> Optional<out T>.toList(): List<T> {
    return if (this.isPresent) listOf(this.get()) else emptyList()
}