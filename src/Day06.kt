import java.lang.Error

fun main() {


    fun part1(input: List<String>): Int {
        val output = input.first().windowed(4,1).indexOfFirst {
            it.toCharArray().groupBy { it }.count() == 4
        }
        return output + 4
    }

    fun part2(input: List<String>): Int {
        val output = input.first().windowed(14,1).indexOfFirst {
            it.toCharArray().groupBy { it }.count() == 14
        }
        return output + 14
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_example")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)
    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}


