package com.adventofcode

import java.io.File

object Utils {
    fun readInts(fileName: String): List<Int> {
        return File(fileName).readLines().map(String::toInt)
    }
}
