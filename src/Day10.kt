
fun main() {

    fun calculateValue(cycle:Int, v: Int): Int{
        if(cycle%40==20){
            return cycle*v
         }
        return 0
    }

    fun part1(input: List<String>): Int {
        var cycle = 1
        var value = 1
        var sum = 0
        input.forEach {
            val split = it.split(" ")
            if(split[0]=="addx"){
                val v = split[1].toInt()
                cycle++
                sum+=calculateValue(cycle, value)
                cycle++
                value+=v
                sum+=calculateValue(cycle, value)
            }
            else {
                cycle++
                sum+=calculateValue(cycle,value)
            }

        }

        return sum
    }


    fun draw(cycle: Int, v: Int ): String{
        if(cycle%40 in v-1..v+1){
            return "#"
        } else {
            return "."
        }
    }

    fun part2(input: List<String>): List<String> {
        var cycle = 0
        var value = 1
        var sum = ""
        input.forEach {
            val split = it.split(" ")
            if(split[0]=="addx"){
                val v = split[1].toInt()

                sum+=draw(cycle, value)
                cycle++
                sum+=draw(cycle, value)
                cycle++
                value+=v
            }
            else {
                sum+=draw(cycle,value)
                cycle++
            }

        }
        return sum.windowed(40, 40)
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_example")
    check(part1(testInput) == 13140)


    val solutionPart2 = listOf("##..##..##..##..##..##..##..##..##..##..",
    "###...###...###...###...###...###...###.",
    "####....####....####....####....####....",
        "#####.....#####.....#####.....#####.....",
        "######......######......######......####",
        "#######.......#######.......#######....."
    )
    check(part2(testInput) == solutionPart2)
    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
    part2(input).forEach {
        println(it)
    }
}


