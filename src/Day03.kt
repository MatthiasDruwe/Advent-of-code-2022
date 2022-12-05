
fun main() {


    fun part1(input: List<String>): Int {
        val output = input.mapNotNull {
            var x: Char? = null
            it.substring(0, it.length / 2).forEach { char ->
                if (it.substring(it.length / 2).contains(char)) {
                    x = char
                }
            }

            x
        }.sumOf {
            if (it.isLowerCase()) {
                it - 'a' + 1
            } else {
                it - 'A' + 27
            }
        }
        return output
        }


        fun part2(input: List<String>): Int {
            val output = input.withIndex().groupBy { it.index / 3 }.mapNotNull {
                var x: Char? = null
                it.value[0].value.forEach { char ->
                    if (it.value[1].value.contains(char) && it.value[2].value.contains(char)) {
                        x = char
                    }
                }
                x
            }.sumOf {
                if (it.isLowerCase()) {
                    it - 'a' + 1
                } else {
                    it - 'A' + 27
                }
            }
            return output
        }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_example")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}


