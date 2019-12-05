package com.adventofcode

fun main() {
    val candidates = (193651..649729).asSequence().mapNotNull { split(it) }
    println("Part 1: ${candidates.filter { it.any { p -> p.second > 1 } }.count()}")
    println("Part 2: ${candidates.filter { it.any { p -> p.second == 2 } }.count()}")
}

fun split(n: Int, collected: List<Pair<Int, Int>> = emptyList()): List<Pair<Int, Int>>? {
    if (n == 0) {
        return collected
    }
    val currDigit = n % 10
    val prevDigit = collected.lastOrNull()
    return when {
        prevDigit == null || currDigit < prevDigit.first -> split(n / 10, collected + (currDigit to 1))
        (currDigit == prevDigit.first) -> {  split(n / 10, collected.dropLast(1) + (prevDigit.first to prevDigit.second + 1))}
        else -> null
    }
}
//
//fun validate(n: Int): Boolean {
//    return validateDigit(n, null, false)
//}
//
//fun validateDigit(n: Int, prevDigit: Int?, b: Boolean): Boolean {
//    return if (n == 0) {
//        b
//    } else if (prevDigit != null) {
//        val currDigit = n % 10
//        if (currDigit > prevDigit) {
//            false
//        } else {
//            validateDigit(n / 10, currDigit, currDigit == prevDigit || b)
//        }
//    } else {
//        validateDigit(n / 10, n % 10, b)
//    }
//}
