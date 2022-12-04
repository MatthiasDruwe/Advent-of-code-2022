
fun main() {


    fun part1(input: List<String>): Int {
        val output = input.map { it.split(",").map { minmax -> minmax.split("-").map { number-> number.toInt() } } }.map{
            (it[0][0]>= it[1][0] && it[0][1]<=it[1][1]) || (it[1][0] >= it[0][0] && it[1][1]<= it[0][1])
        }.count { it }
        return output
    }


    fun part2(input: List<String>): Int {
        val output = input.map { it.split(",").map { minmax -> minmax.split("-").map { number-> number.toInt() } } }.map {
            (it[0][0] >= it[1][0] && it[0][1] <= it[1][0]) || (it[1][0] >= it[0][0] && it[1][0] <= it[0][1]) || (it[0][1] >= it[1][0] && it[0][1] <= it[1][1])
                    || (it[1][1]>=it[0][0] && it[1][1]<=it[0][1])
        }.count { it }
        return output
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_example")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)
    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}


