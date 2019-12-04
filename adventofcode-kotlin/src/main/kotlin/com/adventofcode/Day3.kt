package com.adventofcode

import java.lang.IllegalArgumentException
import kotlin.math.abs

fun main() {
    val input = Utils.readLines("../input/day3.txt") { s ->
        s.split(",").map { it[0] to it.substring(1).toInt() }
    }

    val wires = input.foldIndexed(mapOf<Pair<Int, Int>, List<Pair<Int, Int>>>()) { n, map, path ->
        (map.asSequence() + buildPath(n, path).asSequence())
            .distinct()
            .groupBy({ it.key }, { it.value })
            .mapValues { (_, v) -> v.flatten().distinct() }
    }

    val intersections = wires.filterValues { it.size > 1 }

    val minDistance = intersections.keys.map { abs(it.first) + abs(it.second) }.min()
    println("Part 1. Min distance = $minDistance")

    val bestIntersection = intersections.values.map { it.map { p -> p.second }.sum() }.min()
    println("Part 2. Best intersection = $bestIntersection")
}

fun buildPath(n: Int, path: List<Pair<Char, Int>>): Map<Pair<Int, Int>, List<Pair<Int, Int>>> {
    var currentPosition = Pair(0, 0)
    var stepCounter = 0
    var map = mutableMapOf(currentPosition to listOf(0 to stepCounter))
    for (step in path) {
        val direction = getDirection(step.first)
        for (i in 1..step.second) {
            stepCounter += 1
            currentPosition = direction(currentPosition)
            map[currentPosition] = listOf(n to stepCounter)
        }
    }
    return map
}

fun getDirection(dir: Char): (Pair<Int, Int>) -> Pair<Int, Int> {
    return when (dir) {
        'U' -> { p -> (p.first + 1) to p.second }
        'D' -> { p -> (p.first - 1) to p.second }
        'R' -> { p -> p.first to (p.second + 1) }
        'L' -> { p -> p.first to (p.second - 1) }
        else -> throw IllegalArgumentException("Unknown direction")
    }
}
