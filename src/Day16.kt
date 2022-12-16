import kotlin.math.ceil

fun main() {

    data class Valve(val name: String, val pressure: Int, var pipesTo: List<String>, var open: Boolean = false)


    fun parseInpunt(input: List<String>) = input.map {
        val line = it.split(";")
        val name = line[0].split(" ")[1]
        val pressure = line[0].split(" ")[4].split("=")[1].toInt()
        val pipesTo =
            line[1].trim().removePrefix("tunnels lead to valves ").removePrefix("tunnel leads to valve ").split(",")
                .map { it.trim() }

        Valve(name, pressure, pipesTo)
    }.groupBy { it.name }.mapValues { it.value.first() }

    var maxcost = 0
    fun calculateCost(remainingTime: Int, position: String, paths: Map<String, Valve>, path: String, cost: Int) {
        val valve = paths[position] ?: return
        val unvisitedValves = valve.pipesTo.filter { !path.contains(it) }
        val valves = (unvisitedValves.ifEmpty { valve.pipesTo }).filter { it != position }
        var time = remainingTime
        val sum = paths.filter { !it.value.open }.values.sortedByDescending { it.pressure }.sumOf {
            time = time - 1
            it.pressure * time
        }
        if (remainingTime <= 1 || paths.all { it.value.pressure == 0 || it.value.open } || maxcost > cost + sum) {
            if (maxcost < cost) {
                maxcost = cost
                print(path)
                println(maxcost)
            }
            return
        }
        valves.forEach {
            calculateCost(remainingTime - 1, it, paths, "$path $it", cost)
        }
        if (valve.pressure != 0 && !valve.open) {
            valves.forEach {

                calculateCost(
                    remainingTime - 2,
                    it,
                    paths.mapValues { it.value.copy(open = if (it.value.name == position) true else it.value.open) },
                    "$path $it",
                    cost + (remainingTime - 1) * valve.pressure
                )
            }
        }
    }


    fun calculateCost(
        remainingTime: Int,
        position1: String,
        position2: String,
        paths: Map<String, Valve>,
        path1: String,
        path2: String,
        cost: Int
    ) {
        val valve1 = paths[position1] ?: return
        val valve2 = paths[position2] ?: return
        val unvisitedValves1 = valve1.pipesTo.filter { !path1.contains(it) && !path2.contains(it) }
        val unvisitedValves2 = valve2.pipesTo.filter { !path1.contains(it) && !path2.contains(it) }
        val valves1 = (unvisitedValves1.ifEmpty { valve1.pipesTo }).filter { it != position1 }
        val valves2 = (unvisitedValves2.ifEmpty { valve2.pipesTo }).filter { it != position2 }

        var time = remainingTime
        val sum = paths.filter { !it.value.open }.values.sortedByDescending { it.pressure }.sumOf {
            time--

            it.pressure * (ceil(time.toDouble() / 2.0) * 2 + time % 2)
        }
        if (remainingTime <= 1 || paths.all { it.value.pressure == 0 || it.value.open } || maxcost > cost + sum) {
            if (maxcost < cost) {
                maxcost = cost
                print(path1)
                print("-")
                print(path2)
                println(maxcost)
            }
            return
        }

        if (valve2.pressure != 0 && !valve2.open && valve1.pressure != 0 && !valve1.open && valve1 != valve2) {


            calculateCost(
                remainingTime - 1,
                position1,
                position2,
                paths.mapValues { it.value.copy(open = if (it.value.name == valve2.name || it.value.name == valve1.name) true else it.value.open) },
                path1,
                path2,
                cost + (remainingTime - 1) * valve2.pressure + (remainingTime - 1) * valve1.pressure
            )

        }

        if (valve1.pressure != 0 && !valve1.open) {
            valves2.forEach {

                calculateCost(
                    remainingTime - 1,
                    position1,
                    it,
                    paths.mapValues { it.value.copy(open = if (it.value.name == valve1.name) true else it.value.open) },
                    path1,
                    "$path2 $it",
                    cost + valve1.pressure * (remainingTime - 1)
                )
            }
        }

        if (valve2.pressure != 0 && !valve2.open) {
            valves1.forEach {

                calculateCost(
                    remainingTime - 1,
                    it,
                    position2,
                    paths.mapValues { it.value.copy(open = if (it.value.name == valve2.name) true else it.value.open) },
                    "$path1 $it",
                    path2,
                    cost + (remainingTime - 1) * valve2.pressure
                )
            }
        }

        valves1.forEach { v1 ->
            valves2.forEach { v2 ->
                calculateCost(remainingTime - 1, v1, v2, paths, "$path1 $v1", "$path2 $v2", cost)
            }
        }

    }

    fun part1(input: List<String>): Int {
        var parsedInput = parseInpunt(input)
        maxcost = 0

//        parsedInput = parsedInput.mapValues {
//            it.value.pipesTo = it.value.pipesTo.sortedByDescending { parsedInput[it]?.pressure }
//            it.value
//        }

        val x = calculateCost(30, "AA", parsedInput, "AA", 0)

        println(maxcost)
        return maxcost
    }

    fun part2(input: List<String>): Int {
        var parsedInput = parseInpunt(input)
        maxcost = 0

//        parsedInput = parsedInput.mapValues {
//            it.value.pipesTo = it.value.pipesTo.sortedByDescending { parsedInput[it]?.pressure }
//            it.value
//        }

        val x = calculateCost(26, "AA", "AA", parsedInput, "AA", "AA", 0)

        println(maxcost)
        return maxcost
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day16_example")
    check(part1(testInput) == 1651)
    check(part2(testInput) == 1707)
    val input = readInput("Day16")
    println(part1(input))
    println(part2(input))
}

