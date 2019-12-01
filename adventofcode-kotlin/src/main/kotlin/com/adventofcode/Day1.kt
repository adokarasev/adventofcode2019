package com.adventofcode

fun main() {
    val input = Utils.readInts("../input/day1.txt")

    input
        .fold(0) { acc, c -> acc + c / 3 - 2 }
        .let { println("1. Total fuel: $it") }

    input
        .fold(0) { acc, c -> acc + calculateRequiredFuel(c) }
        .let { println("2. Total fuel: $it") }
}

fun calculateRequiredFuel(m: Int): Int {
    val f = m / 3 - 2
    return if (f < 0) 0 else f + calculateRequiredFuel(f)
}
