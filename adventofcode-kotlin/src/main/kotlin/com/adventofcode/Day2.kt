package com.adventofcode

fun main() {
    val input = Utils.readLines("../input/day2.txt") { s: String -> s.split(",").map(String::toInt) }
    println("Part 1. Answer = ${executeProgram(input.first(), 12, 2)}")

    for (noun in 1..100) {
        for (verb in 1..100) {
            if (executeProgram(input.first(), noun, verb) == 19690720) {
                println("Part 2. Answer = ${100 * noun + verb}")
                return
            }
        }
    }
}

fun executeProgram(input: List<Int>, noun: Int, verb: Int): Int {
    val program = input.toMutableList()
    program[1] = noun
    program[2] = verb
    executeNextCommand(program, 0)
    return program[0]
}

fun executeNextCommand(program: MutableList<Int>, pos: Int) {
    val command = program[pos]
    if (command == 1 || command == 2) {
        val (_, a, b, c) = program.subList(pos, program.size)
        when (command) {
            1 -> program[c] = program[a] + program[b]
            2 -> program[c] = program[a] * program[b]
        }
        executeNextCommand(program, pos + 4)
    }
}
