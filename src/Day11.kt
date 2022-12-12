import kotlin.math.floor

fun main() {


    fun part1(input: List<String>): Long {
        val monkeys = input.splitOnEmptyString().map { Monkey(it.drop(1)) }

        repeat(20){
            monkeys.forEach { monkey->
                repeat(monkey.numbers.size) {
                    monkey.inspect()
                    val number = monkey.numbers.removeFirst()
                    val newNumber = floor(monkey.executeOperation(number).toDouble()/3).toLong()
                    if(newNumber%monkey.test==0L){
                        monkeys[monkey.trueMonkey].numbers.add(newNumber)
                    } else {
                        monkeys[monkey.falseMonkey].numbers.add(newNumber)
                    }
                }
            }

        }
        val orderMonkeys = monkeys.sortedByDescending { it.inspectCount }
        return orderMonkeys[0].inspectCount * orderMonkeys[1].inspectCount
    }


    fun part2(input: List<String>): Long {
        val monkeys = input.splitOnEmptyString().map { Monkey(it.drop(1)) }
        var maxTest =1L
            monkeys.forEach { maxTest*=it.test }
        repeat(10000){
            monkeys.forEach { monkey->
                repeat(monkey.numbers.size) {
                    monkey.inspect()
                    val number = monkey.numbers.removeFirst()
                    val newNumber = monkey.executeOperation(number) % maxTest
                    if(newNumber%monkey.test==0L){
                        monkeys[monkey.trueMonkey].numbers.add(newNumber)
                    } else {
                        monkeys[monkey.falseMonkey].numbers.add(newNumber)
                    }
                }
            }

        }
        val orderMonkeys = monkeys.sortedByDescending { it.inspectCount }
        return orderMonkeys[0].inspectCount * orderMonkeys[1].inspectCount
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_example")
    check(part1(testInput) == 10605L)
    check(part2(testInput) == 2713310158L)
    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}

class Monkey(input: List<String>){
    val numbers: MutableList<Long>
    private val operation: List<String>
    val test: Int
    val trueMonkey: Int
    val falseMonkey: Int
    var inspectCount =0L

    init {
        numbers = input[0].trim().removePrefix("Starting items: ").split(", ").map { it.toLong() }.toMutableList()
        operation = input[1].trim().removePrefix("Operation: new = ").split(" ")
        test = input[2].trim().removePrefix("Test: divisible by ").toInt()
        trueMonkey = input[3].trim().removePrefix("If true: throw to monkey ").toInt()
        falseMonkey = input[4].trim().removePrefix("If false: throw to monkey ").toInt()
    }

    fun inspect(){
        inspectCount++
    }
    fun executeOperation(old: Long): Long{
        val number1 = if(operation[0]=="old") old else operation[0].toLong()
        val number2 = if(operation[2]=="old") old else operation[2].toLong()

        return when(operation[1]){
            "+" -> number1+ number2
            "-" -> number1- number2
            "*" -> number1* number2
            "/" -> number1/ number2
            else -> 0
        }
    }
}


