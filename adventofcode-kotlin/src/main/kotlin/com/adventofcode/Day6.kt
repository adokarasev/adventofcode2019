package com.adventofcode

import java.lang.Exception

fun main() {
    val input = Utils.readLines("../input/day6.txt") { _, line ->
        line.split("\\)".toRegex(), 2).let { (a, b) -> b to a }
    }.fold(emptyMap<String, String>(), { acc, p -> acc + p })

    val orbits = input.mapValues { (k, v) -> countOrbits(input, k) }
    println("Part 1. Total orbits = ${orbits.values.sum()}")

    val pathToYou = pathTo(input, "YOU")
    val pathToSanta = pathTo(input, "SAN")
    val len = (pathToSanta + pathToYou).groupBy { it }.filter { (_, v) -> v.size == 1 }.size
    println("Part 2. Path = ${len - 2}")
}

fun countOrbits(map: Map<String, String>, obj: String?): Int {
    return when (obj) {
        null -> throw Exception("WRONG INPUT")
        "COM" -> 0
        else -> 1 + countOrbits(map, map[obj])
    }
}

fun pathTo(map: Map<String, String>, obj: String?): List<String> {
    return when (obj) {
        null -> throw Exception("WRONG INPUT")
        "COM" -> listOf("COM")
        else -> pathTo(map, map[obj]) + obj
    }
}
