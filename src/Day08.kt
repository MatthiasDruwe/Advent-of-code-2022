import java.lang.Error

fun main() {


    fun part1(input: List<String>): Int {
        val output = input.map { line -> line.toCharArray().map { it.toString().toInt() } }
        val visibleFromLeft = output. map { row->
            row.mapIndexed{index, value->
                var visible = true
                repeat(index){
                    if(row[index - 1 - it]>=value){
                        visible = false
                    }
                }
                visible
            }
        }

        val visibleFromRight = output.map { row->
            row.mapIndexed{index, value->
                var visible = true
                repeat(row.size-index-1){
                    if(row[index + it + 1]>=value){
                        visible = false
                    }
                }
                visible
            }
        }
        val visibleFromTop = output.transpose().map { row->
            row.mapIndexed{index, value->
                var visible = true
                repeat(index){
                    if(row.toList()[index - 1 - it]>=value){
                        visible = false
                    }
                }
                visible
            }
        }.transpose().toList()


        val visibleFromBottom = output.transpose().map { row->
            row.mapIndexed{index, value->
                var visible = true
                repeat(row.toList().size-index-1){
                    if(row.toList()[index + it + 1]>=value){
                        visible = false
                    }
                }
                visible
            }
        }.transpose().toList()


        val result = output.mapIndexed{ i,row->
            row.mapIndexed { j, _ ->
                visibleFromLeft[i][j] || visibleFromRight[i][j] || visibleFromTop[i].toList()[j] || visibleFromBottom[i].toList()[j]
            }
        }.sumOf { row -> row.count { it } }

        return  result
    }

    fun part2(input: List<String>): Int { val output = input.map { line -> line.toCharArray().map { it.toString().toInt() } }
        val visibleFromLeft = output. map { row->
                row.mapIndexed{index, value->
                    var count = 0
                    while (index - count -1 >= 0 && row[index - count-1] < value)
                    {
                        count++
                    }
                    if(index - count!=0){
                        count++
                    }
                    count
                }

        }

        val visibleFromRight = output. map { row->
            row.mapIndexed{index, value->
                var count = 0
                while (index+count+1 < row.size && row[index+count+1] < value)
                {
                    count++
                }
                if(index + count+1 != row.size){
                    count++
                }
                count
            }

        }
        val visibleFromTop = output.transpose(). map { row->
            row.mapIndexed{index, value->
                var count = 0
                while (index - count -1 >= 0 && row.toList()[index - count-1] < value)
                {
                    count++
                }
                if(index - count!=0){
                    count++
                }
                count
            }

        }.transpose().toList()


        val visibleFromBottom = output.transpose().map { row->
            row.mapIndexed{index, value->
                var count = 0
                while (index+count+1 < row.toList().size && row.toList()[index+count+1] < value)
                {
                    count++
                }
                if(index + count+1 != row.toList().size){
                    count++
                }
                count
            }

        }.transpose().toList()

        val result = output.mapIndexed{ i,row->
            row.mapIndexed { j, _ ->
                visibleFromLeft[i][j] * visibleFromRight[i][j] * visibleFromTop[i].toList()[j] * visibleFromBottom[i].toList()[j]
            }
        }.maxOf { row -> row.maxOf { it } }

        return  result
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_example")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)
    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}


