
fun main() {


    fun part1(input: List<String>): Int {
       val monkeys = input.
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
    val testInput = readInput("Day11_example")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)
    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}

class Monkey(input: List<String>){
    private val numbers: MutableList<Int>
    val operation: List<String>
    val test: Int
    val trueMonkey: Int
    val falseMonkey: Int

    init {
        numbers = input[0].trim().removePrefix("Starting items: ").split(", ").map { it.toInt() }.toMutableList()
        operation = input[1].trim().removePrefix("Operation: new = ").split(" ")
        test = input[2].trim().removePrefix("Test: divisible by ").toInt()
        trueMonkey = input[3].trim().removePrefix("If true: throw to monkey ").toInt()
        falseMonkey = input[4].trim().removePrefix("If false: throw to monkey ").toInt()
    }

    fun executeOperation(old: Int): Int{
        val number1 = if(operation[0]=="old") old else operation[0].toInt()
        val number2 = if(operation[2]=="old") old else operation[2].toInt()

        return when(operation[1]){
            "+" -> number1+ number2
            "-" -> number1- number2
            "*" -> number1* number2
            "/" -> number1/ number2
            else -> 0
        }
    }
}


