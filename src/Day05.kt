fun main() {


    fun part1(input: List<String>): String {
        val start = input.subList(0, input.indexOf(""))
        val actions = input.subList(input.indexOf("") + 1, input.size).map {
            val items = it.split(" ")
            Triple(items[1].toInt(), items[3].toInt(), items[5].toInt())
        }
        val items = start
            .map { line -> line.chunked(4).map { if (it[1] == ' ') null else it[1] } }
            .asReversed()
            .drop(1)
            .transpose()
            .map { it.filterNotNull().toMutableList() }
            .toMutableList()


        actions.forEach {
            val away = items[it.second-1].takeLast(it.first)
            items[it.second-1] = items[it.second-1].dropLast(it.first).toMutableList()
            items[it.third-1].addAll(away.reversed())
        }

        return items.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val start = input.subList(0, input.indexOf(""))
        val actions = input.subList(input.indexOf("") + 1, input.size).map {
            val items = it.split(" ")
            Triple(items[1].toInt(), items[3].toInt(), items[5].toInt())
        }
        val items = start
            .map { line -> line.chunked(4).map { if (it[1] == ' ') null else it[1] } }
            .asReversed()
            .drop(1)
            .transpose()
            .map { it.filterNotNull().toMutableList() }
            .toMutableList()


        actions.forEach {
            val away = items[it.second-1].takeLast(it.first)
            items[it.second-1] = items[it.second-1].dropLast(it.first).toMutableList()
            items[it.third-1].addAll(away)
        }

        return items.map { it.last() }.joinToString("")
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_example")
    check(part1(testInput) == "CMZ" )
    check(part2(testInput) == "MCD")
    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}


