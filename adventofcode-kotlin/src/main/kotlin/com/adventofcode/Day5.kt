package com.adventofcode

fun main() {
    val memory = Utils.readLines("../input/day5.txt") { s: String -> s.split(",").map(String::toInt) }.first()
    val computer = IntCodeComputer(
        memory = memory.toMutableList(),
        input = mutableListOf(5)
    )

    print("Part 1. Answer: ???")

    val output = computer.execute()
    print("Part 2. Answer: ${output.joinToString(separator = ",")}")
}
