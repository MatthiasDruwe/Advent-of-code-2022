import java.lang.Error

fun main() {


    fun part1(input: List<String>): Int {
        val output = input.map { it.split(" ").map { it.convert() } }.sumOf {it[1].value + it[1].score(it[0]) }
        return output
    }

    fun part2(input: List<String>): Int {
        val output = input.map{convertToPair(it)}.also { println(it) }.sumOf { it.second.value + it.second.score(it.first) }
        return output
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_example")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)
    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Hand(val value: Int){
    Rock(1), Paper(2), Scissors(3)
}
fun String.convert(): Hand{
    return when(this){
        "A", "X" -> Hand.Rock
        "B", "Y" -> Hand.Paper
        "C", "Z" -> Hand.Scissors
        else -> throw Exception()
    }
}

fun convertToPair(input: String): Pair<Hand, Hand>{
    val items = input.split(" ")
    val item1 = items[0].convert()

    val item2 = when(items[1]){
        "X" -> ((item1.value -1)%3).toHand()
        "Y" -> item1
        "Z" -> ((item1.value +1)%3).toHand()
        else -> throw Exception()
    }
    return Pair(item1, item2)
}
fun Hand.score(opponent: Hand): Int{
    if(this==opponent){
        return 3
    }
    if((this.value+1)%3==opponent.value%3){
        return 0
    }

    if((this.value-1)%3 == opponent.value%3){
        return 6
    }
    return 0
}

fun Int.toHand(): Hand {
   return when(this){
        1 -> Hand.Rock
        2-> Hand.Paper
        0, 3-> Hand.Scissors
        else -> throw Exception()
    }
 }

