package com.adventofcode

fun main() {
    val memory = Utils.readLines("../input/day7.txt") { s: String -> s.split(",").map(String::toInt) }.first()
    val permutations = permute((0..4).toList())
    val highestSignal = permutations
        .map {
            println("Trying: $it")
            it.fold(0) { i, f ->
                IntCodeComputer(memory.toMutableList(), listOf(f, i)).execute().first()
            }
        }
        .max()

    println("Part 1. Highest signal = $highestSignal")

    val permutations1 = permute((5..9).toList())

}

fun <T> permute(list: List<T>): List<List<T>> {
    return when {
        list.size == 1 -> listOf(list)
        else -> list.flatMap { n -> permute(list - listOf(n)).map { listOf(n) + it  } }
    }
}
