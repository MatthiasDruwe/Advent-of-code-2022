import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {


    fun part1(input: List<String>, y: Long): Int {
        val sensors = input.map {
            val values = it.split(" ")
            val sensorX = values[2].split("=")[1].removeSuffix(",").toLong()
            val sensorY = values[3].split("=")[1].removeSuffix(":").toLong()
            val beaconX = values[8].split("=")[1].removeSuffix(",").toLong()
            val beaconY = values[9].split("=")[1].toLong()

            Pair(Pair(sensorX, sensorY), Pair(beaconX, beaconY))
        }

        val noBeacons = mutableSetOf<Pair<Long, Long>>()

        sensors.forEach { pair ->
            val sensor = pair.first
            val beacon = pair.second
            val distance =
                abs(sensor.first - beacon.first) + abs(sensor.second - beacon.second)


            if (distance - abs(sensor.second - y) >= 0) {


                val x1 = distance - abs(sensor.second - y) + sensor.first
                val x2 = -(distance - abs(sensor.second - y)) + sensor.first



                for (i in min(x1, x2)..max(x1, x2)) {
                    noBeacons.add(Pair(i, y))
                }
            }
        }

        println(noBeacons.size)

        val output =
            noBeacons.count { nobeacon -> sensors.find { it.first == nobeacon || it.second == nobeacon } == null }
        println(output)
        return output
    }

    fun manhattanDistance(pair1: Pair<Long, Long>, pair2: Pair<Long, Long>): Long {
        return abs(pair1.first - pair2.first) + abs(pair1.second - pair2.second)
    }

    fun part2(input: List<String>, size: Int): Long {


        val sensors = input.map {
            val values = it.split(" ")
            val sensorX = values[2].split("=")[1].removeSuffix(",").toLong()
            val sensorY = values[3].split("=")[1].removeSuffix(":").toLong()
            val beaconX = values[8].split("=")[1].removeSuffix(",").toLong()
            val beaconY = values[9].split("=")[1].toLong()

            Pair(Pair(sensorX, sensorY), Pair(beaconX, beaconY))
        }

        var point = Pair(0L, 0L)
        for (y in 0..size) {
            var x = 0
            while (x <= size) {
                val visibleSensors = sensors.filter {
                    manhattanDistance(it.first, Pair(x.toLong(), y.toLong())) <= manhattanDistance(it.first, it.second)
                }

                if (visibleSensors.isEmpty()) {
                    point = Pair(x.toLong(), y.toLong())
                    break
                }
                x = visibleSensors.maxOf {
                    manhattanDistance(
                        it.first,
                        it.second
                    ) - abs(it.first.second - y) + it.first.first
                }
                    .toInt() + 1
            }
            if (point != Pair(0L, 0L)) {
                break
            }

        }

        return point.first * 4000000 + point.second
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_example")
    check(part1(testInput, 10) == 26)
    check(part2(testInput, 20) == 56000011L)
    val input = readInput("Day15")
    println(part1(input, 2000000))
    println(part2(input, 4000000))
}

