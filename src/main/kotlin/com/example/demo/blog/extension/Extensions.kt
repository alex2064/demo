package com.example.demo.blog.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

/**
 * 확장함수
 *      기존 클래스에 내가 사용할 함수를 추가해서 생성
 */
fun LocalDateTime.format(): String = this.format(englishDateFormatter)

private val daysLookup = (1..31).associate { n -> n.toLong() to getOrdinal(n) }

private val englishDateFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd")
    .appendLiteral(" ")
    .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
    .appendLiteral(" ")
    .appendPattern("yyyy")
    .toFormatter(Locale.ENGLISH)


private fun getOrdinal(num: Int): String {
    return when {
        num in 11..13 -> "${num}th"
        num % 10 == 1 -> "${num}st"
        num % 10 == 2 -> "${num}nd"
        num % 10 == 3 -> "${num}rd"
        else -> "${num}th"
    }
}

fun String.toSlug(): String {
    return lowercase(Locale.getDefault())
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString ("-")
        .replace("-+".toRegex(), "-")
}