package com.adventofcode

import java.nio.file.Files
import java.nio.file.Paths

object Utils {

    fun readInts(fileName: String) = readLines(fileName) { s: String -> s.toInt() }

    fun <T> readLines(fileName: String, f: (String) -> T): List<T> {
        return readLines(fileName) { _, s: String -> f(s) }
    }

    fun <T> readLines(fileName: String, f: (Int, String) -> T): List<T> {
        return Files.readAllLines(Paths.get(fileName)).mapIndexed(f)
    }
}
