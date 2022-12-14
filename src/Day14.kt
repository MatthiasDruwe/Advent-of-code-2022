import kotlin.math.max
import kotlin.math.min

fun main() {


    fun part1(input: List<String>): Int {
        val rocks = mutableSetOf<Pair<Int, Int>>()
        input.forEach { row ->
            row.split(" -> ").map { point ->
                val x = point.split(",")

                Pair(x[0].toInt(), x[1].toInt())
            }.windowed(2, 1) {

                if (it[0].first == it[1].first) {
                    val min = min(it[0].second, it[1].second)
                    val max = max(it[0].second, it[1].second)
                    for (i in min..max) {
                        rocks.add(Pair(it[0].first, i))
                    }
                }
                if (it[0].second == it[1].second) {
                    val min = min(it[0].first, it[1].first)
                    val max = max(it[0].first, it[1].first)
                    for (i in min..max) {
                        rocks.add(Pair(i, it[0].second))
                    }
                }
            }
        }

        val maxdepth = rocks.maxOf { it.second }

        var currentSand = Pair(500, 0)
        var count = 0

        while (currentSand.second < maxdepth) {
            if (rocks.firstOrNull { it == Pair(currentSand.first, currentSand.second + 1) } == null) {
                currentSand = Pair(currentSand.first, currentSand.second + 1)
            } else if (rocks.firstOrNull { it == Pair(currentSand.first - 1, currentSand.second + 1) } == null) {
                currentSand = Pair(currentSand.first - 1, currentSand.second + 1)
            } else if (rocks.firstOrNull { it == Pair(currentSand.first + 1, currentSand.second + 1) } == null) {
                currentSand = Pair(currentSand.first + 1, currentSand.second + 1)
            } else {
                count++
                rocks.add(currentSand)
                currentSand = Pair(500, 0)
            }
        }

        return count
    }

    fun part2(input: List<String>): Long {
        val rocks = mutableSetOf<Pair<Int, Int>>()
        input.forEach { row ->
            row.split(" -> ").map { point ->
                val x = point.split(",")

                Pair(x[0].toInt(), x[1].toInt())
            }.windowed(2, 1) {

                if (it[0].first == it[1].first) {
                    val min = min(it[0].second, it[1].second)
                    val max = max(it[0].second, it[1].second)
                    for (i in min..max) {
                        rocks.add(Pair(it[0].first, i))
                    }
                }
                if (it[0].second == it[1].second) {
                    val min = min(it[0].first, it[1].first)
                    val max = max(it[0].first, it[1].first)
                    for (i in min..max) {
                        rocks.add(Pair(i, it[0].second))
                    }
                }
            }
        }

        val maxdepth = rocks.maxOf { it.second }

        var currentSand = Pair(500, 0)
        var count = 0L

        val indexRocks =
            rocks.groupBy { it.first }.mapValues { it.value.sortedBy { it.second }.toMutableList() }.toMutableMap()

        while (!rocks.contains(Pair(500, 0))) {
            if (indexRocks[currentSand.first]?.contains(Pair(currentSand.first, currentSand.second + 1)) != true) {

                val temp = indexRocks[currentSand.first]?.firstOrNull { it.second > currentSand.second }
                currentSand = temp?.copy(second = temp.second - 1) ?: Pair(currentSand.first, maxdepth + 1)
            } else if (indexRocks[currentSand.first - 1]?.contains(
                    Pair(
                        currentSand.first - 1,
                        currentSand.second + 1
                    )
                ) != true
            ) {

                val temp = indexRocks[currentSand.first - 1]?.firstOrNull { it.second > currentSand.second }
                currentSand = temp?.copy(second = temp.second - 1) ?: Pair(currentSand.first - 1, maxdepth + 1)
            } else if (indexRocks[currentSand.first + 1]?.contains(
                    Pair(
                        currentSand.first + 1,
                        currentSand.second + 1
                    )
                ) != true
            ) {

                val temp = indexRocks[currentSand.first + 1]?.firstOrNull { it.second > currentSand.second }
                currentSand = temp?.copy(second = temp.second - 1) ?: Pair(currentSand.first + 1, maxdepth + 1)
            } else {
                count++
                rocks.add(currentSand)
                if (indexRocks[currentSand.first] != null) {
                    indexRocks[currentSand.first]?.add(currentSand)
                    indexRocks[currentSand.first]?.sortBy { it.second }
                } else {
                    indexRocks[currentSand.first] = mutableListOf(currentSand)
                }
                currentSand = Pair(500, 0)
            }
            if (currentSand.second == maxdepth + 1) {
                count++
                rocks.add(currentSand)
                if (indexRocks[currentSand.first] != null) {
                    indexRocks[currentSand.first]?.add(currentSand)
                    indexRocks[currentSand.first]?.sortBy { it.second }
                } else {
                    indexRocks[currentSand.first] = mutableListOf(currentSand)
                }
                currentSand = Pair(500, 0)
            }
        }

        return count
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_example")
    check(part1(testInput) == 24)
    check(part2(testInput) == 93L)
    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}

