import kotlin.math.abs


fun eq(item1: MutableList<Any>, item2: MutableList<Any>): Int {
    repeat(item1.size) { x ->
        if (x == item2.size) {
            return -1
        }

        if (item1[x] is MutableList<*> && item2[x] is MutableList<*>) {
            val result = eq(item1[x] as MutableList<Any>, item2[x] as MutableList<Any>)
            if (result != 0) {
                return result
            }
        } else if (item1[x] is Int && item2[x] is Int) {
            if ((item2[x] as Int) - (item1[x] as Int) != 0) {

                return ((item2[x] as Int) - (item1[x] as Int)) / abs((item2[x] as Int) - (item1[x] as Int))
            } else {
                0
            }


        } else if (item1[x] is MutableList<*>) {
            val result = eq(item1[x] as MutableList<Any>, mutableListOf(item2[x]))
            if (result != 0) {
                return result
            }
        } else if (item2[x] is MutableList<*>) {

            val result = eq(mutableListOf(item1[x]), item2[x] as MutableList<Any>)
            if (result != 0) {
                return result
            }
        }

    }

    return if (item2.size > item1.size) 1 else 0

}

class Comp1 : Comparator<MutableList<Any>> {
    override fun compare(o1: MutableList<Any>?, o2: MutableList<Any>?): Int {
        return eq(o1!!, o2!!)
    }

}

fun main() {


    fun part1(input: List<String>): Int {
//        val pairs = input.splitOnEmptyString().map { Pair(it[0].convertToArray(), it[1].convertToArray()) }.map {
//            it.first.compareTo(it.second) <= 0
//        }
//        println(pairs)
        val pairs = input.splitOnEmptyString().map { Pair(it[0].convertToArray(), it[1].convertToArray()) }.map {
            eq(it.first, it.second) >= 0
        }
        println(pairs)
        return pairs.mapIndexed { index, value -> if (value) index + 1 else 0 }.sum()
    }

    fun part2(input: List<String>): Int {
        val divider1: MutableList<Any> = mutableListOf(mutableListOf(2))
        val divider2: MutableList<Any> = mutableListOf(mutableListOf(6))
        val pairs = input.splitOnEmptyString().map { Pair(it[0].convertToArray(), it[1].convertToArray()) }
            .map { listOf(it.first, it.second) }.flatten().toMutableList()
        pairs.add(divider1)
        pairs.add(divider2)
        pairs.sortWith(Comp1())
        pairs.reverse()
        val index1 = pairs.indexOf(divider1) + 1
        val index2 = pairs.indexOf(divider2) + 1

        return index1 * index2
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_example")
    check(part1(testInput) == 13)
    check(part2(testInput) == 140)
    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}

fun String.convertToArray(): MutableList<Any> {

    var array = MutableList<Any>(0) { 0 }
    val values = this.removePrefix("[").removeSuffix("]")
    var count = 0
    var item = ""

    values.forEach {
        if (it == '[') {
            count++
        } else if (it == ']') {
            count--
        }

        if (it == ',' && count == 0) {
            if (item.toIntOrNull() != null) {
                array.add(item.toInt())
                item = ""
            } else if (item != "") {
                array.add(item.convertToArray())
                item = ""
            }
        } else {
            item += it

        }
    }
    if (item.toIntOrNull() != null) {
        array.add(item.toInt())
        item = ""
    } else if (item != "") {
        array.add(item.convertToArray())
    }



    return array
}

