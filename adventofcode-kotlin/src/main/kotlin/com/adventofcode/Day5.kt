package com.adventofcode

import java.lang.IllegalArgumentException

fun main() {
    val memory = Utils.readLines("../input/day5.txt") { s: String -> s.split(",").map(String::toInt) }.first()
    val computer = IntCodeComputer(
        memory = memory.toMutableList(),
        input = mutableListOf(5)
    )

    val output = computer.execute()
    print("Part 1. Answer: ${output.joinToString(separator = ",")}")
}

typealias OpCode = Int

class IntCodeComputer(
    val memory: MutableList<Int>,
    val input: MutableList<Int> = mutableListOf()
) {


    companion object {
        const val Sum = 1
        const val Multiply = 2
        const val Read = 3
        const val Write = 4
        const val JumpIfTrue = 5
        const val JumpIfFalse = 6
        const val LessThan = 7
        const val Equals = 8
        const val Halt = 99

        const val Position = 0
        const val Immediate = 1
    }

    fun execute(currPos: Int = 0): List<Int> {
        val opcode = memory[currPos]
        val (output, nextPos) = when (opcode % 100) {
            Sum -> sum(opcode, currPos)
            Multiply -> multiply(opcode, currPos)
            Read -> readInput(opcode, currPos)
            Write -> writeOutput(opcode, currPos)
            JumpIfTrue -> jumpIfTrue(opcode, currPos)
            JumpIfFalse -> jumpIfFalse(opcode, currPos)
            LessThan -> doLessThan(opcode, currPos)
            Equals -> doEquals(opcode, currPos)
            else -> emptyList<Int>() to -1
        }

        return if (nextPos > 0) {
            output + execute(nextPos)
        } else {
            output
        }
    }

    private fun sum(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        val sum = readValue(c, memory[pos + 1]) + readValue(b, memory[pos + 2])
        storeValue(a, memory[pos + 3], sum)
        return emptyList<Int>() to pos + 4
    }

    private fun multiply(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        val sum = readValue(c, memory[pos + 1]) * readValue(b, memory[pos + 2])
        storeValue(a, memory[pos + 3], sum)
        return emptyList<Int>() to pos + 4
    }

    private fun readInput(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val ind = memory[pos + 1]
        memory[ind] = input.first()
        return emptyList<Int>() to pos + 2
    }


    private fun writeOutput(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val ind = memory[pos + 1]
        return listOf(memory[ind]) to pos + 2
    }

    private fun jumpIfTrue(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        val isTrue = readValue(c, memory[pos + 1]) != 0
        return emptyList<Int>() to if (isTrue) readValue(b, memory[pos + 2]) else pos + 3
    }

    private fun jumpIfFalse(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        val isFalse = readValue(c, memory[pos + 1]) == 0
        return emptyList<Int>() to if (isFalse) readValue(b, memory[pos + 2]) else pos + 3
    }

    private fun doLessThan(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        if (readValue(c, memory[pos + 1]) < readValue(b, memory[pos + 2])) {
            storeValue(a, memory[pos + 3], 1)
        } else {
            storeValue(a, memory[pos + 3], 0)
        }
        return emptyList<Int>() to pos + 4
    }

    private fun doEquals(opcode: Int, pos: Int): Pair<List<Int>, Int> {
        val (a, b, c, _) = opcode
        if (readValue(c, memory[pos + 1]) == readValue(b, memory[pos + 2])) {
            storeValue(a, memory[pos + 3], 1)
        } else {
            storeValue(a, memory[pos + 3], 0)
        }
        return emptyList<Int>() to pos + 4
    }

    private fun readValue(parameterMode: Int, parameterValue: Int) = when (parameterMode) {
        Position -> memory[parameterValue]
        Immediate -> parameterValue
        else -> throw IllegalArgumentException("Unknown parameter mode: $parameterMode")
    }

    private fun storeValue(parameterMode: Int, parameterValue: Int, value: Int) {
        memory[parameterValue] = value
    }
}

private operator fun OpCode.component1(): Int = this / 10000
private operator fun OpCode.component2(): Int = (this / 1000) % 10
private operator fun OpCode.component3(): Int = (this / 100) % 10
private operator fun OpCode.component4(): Int = this % 100
