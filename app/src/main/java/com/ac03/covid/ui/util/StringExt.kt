package com.ac03.covid.ui.util

fun String.changeFormat(): String {
    return this.replace(",", ".")
}
