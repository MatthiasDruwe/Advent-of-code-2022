import java.lang.Error
import javax.swing.text.Position
import kotlin.math.abs

fun main() {

    fun calcPositionT(positionH: Pair<Int,Int>, positionT: Pair<Int, Int>): Pair<Int,Int>{
        if(abs(positionH.first - positionT.first) <= 1 && abs(positionT.second - positionH.second) <= 1){
            return positionT
        }
        if(positionH.first-positionT.first == 0){
            return Pair(positionT.first ,  positionT.second + (positionH.second-positionT.second)/abs(positionH.second-positionT.second))

        } else if (positionH.second-positionT.second == 0){
            return Pair(positionT.first + (positionH.first-positionT.first)/abs(positionH.first-positionT.first),  positionT.second)

        }
        return Pair(positionT.first + (positionH.first-positionT.first)/abs(positionH.first-positionT.first),  positionT.second + (positionH.second-positionT.second)/abs(positionH.second-positionT.second))
    }

    fun part1(input: List<String>): Int {
        val output = input.map {
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }

        val positions = mutableSetOf<Pair<Int, Int>>()
        var positionH = Pair(0,0)
        var positionT = Pair(0,0)
        positions.add(positionT)

        output.forEach {(action, value)->
            repeat(value){
                positionH = when(action){
                    "R"-> Pair(positionH.first+1, positionH.second)
                    "L"-> Pair(positionH.first-1, positionH.second)
                    "U"-> Pair(positionH.first, positionH.second-1)
                    "D"-> Pair(positionH.first, positionH.second+1)
                    else -> positionH
                }
                positionT = calcPositionT(positionH, positionT)
                positions.add(positionT)
            }
        }

        return positions.size
    }



    fun part2(input: List<String>): Int {
        val output = input.map {
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }

        val positions = mutableSetOf<Pair<Int, Int>>()
        var positionH = Pair(0,0)
        var positionT = Array(9){ Pair(0,0)}
        positions.add(positionT.last())

        output.forEach {(action, value)->
            repeat(value){
                positionH = when(action){
                    "R"-> Pair(positionH.first+1, positionH.second)
                    "L"-> Pair(positionH.first-1, positionH.second)
                    "U"-> Pair(positionH.first, positionH.second-1)
                    "D"-> Pair(positionH.first, positionH.second+1)
                    else -> positionH
                }

                repeat(positionT.size){
                    if(it==0){
                        positionT[it]=calcPositionT(positionH, positionT[it])
                    } else{

                        positionT[it]=calcPositionT(positionT[it-1], positionT[it])
                    }
                }
                positions.add(positionT.last())
            }
        }

        return positions.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_example")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}


