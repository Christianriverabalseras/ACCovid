package com.ac03.covid.util

fun String.changeFormat(): String {
    return this.replace(",", ".")
}