package com.adventofcode

fun main() {
    val candidates = (193651..649729).asSequence().mapNotNull { split(it) }
    println("Part 1: ${candidates.filter { it.any { (_, v) -> v > 1 } }.count()}")
    println("Part 2: ${candidates.filter { it.any { (_, v) -> v == 2 } }.count()}")
}

fun split(n: Int, prevDigit: Int? = null, collected: MutableMap<Int, Int> = mutableMapOf()): Map<Int, Int>? {
    if (n == 0) {
        return collected
    }
    val currDigit = n % 10
    return when {
        prevDigit == null || currDigit < prevDigit -> {
            collected[currDigit] = 1
            split(n / 10, currDigit, collected)
        }
        (currDigit == prevDigit) -> {
            collected[prevDigit] = collected[prevDigit]!! + 1
            split(n / 10, currDigit, collected)
        }
        else -> null
    }
}
