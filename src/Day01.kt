fun main() {


    fun part1(input: List<String>): Int {
       return input.splitOnEmpty().maxOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        return input.splitOnEmpty().sortedByDescending { it.sum() }.take(3).sumOf { it.sum() }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_example")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

