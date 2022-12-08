import java.lang.Error

fun main() {




    fun createStructure(input: List<String>): Directory {
        val structure = Directory("root")
        var currentItem: Directory? = null
        val parents = mutableMapOf<String, Directory>()
        input.forEach { line ->
            val items = line.split(" ")
            if (items[0] == "$") {
                if (items[1] == "cd") {
                    if (items[2] == "/") {
                        currentItem = structure
                    } else if (items[2] == "..") {
                        currentItem = parents[currentItem?.name]
                    } else {
                        currentItem = currentItem?.directories?.find { it.name == currentItem?.name + "/" + items[2] }
                    }
                }
            } else {
                if (items[0] == "dir") {
                    val directory = Directory(currentItem?.name + "/" + items[1])
                    currentItem?.directories?.add(directory)
                    currentItem?.also { parents[directory.name] = it }
                } else {
                    currentItem?.files?.add(File(items[1], items[0].toLong()))
                }

            }
        }
        return structure
    }

    fun part1(input: List<String>): Long {
        val structure = createStructure(input)
        val x = structure.getDirectoriesWithSizeBelow(100000)
        return x.sumOf { it.getSize() }
    }

    fun part2(input: List<String>): Long {
        val structure = createStructure(input)
        val totalSize = 30000000 - 70000000 + structure.getSize()
        val items = structure.getDirectoriesWithSizeAbove(totalSize).minBy { it.getSize() }
        return items.getSize()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_example")
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)
    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

data class Directory(val name: String, val directories: MutableList<Directory> = mutableListOf<Directory>(), val files: MutableList<File> = mutableListOf<File>() ){
    fun getDirectoriesWithSizeBelow(number: Long): List<Directory>{
        return  directories.filter { it.getSize() <= number } + directories.map { it.getDirectoriesWithSizeBelow(number) }.flatten()
    }

    fun getDirectoriesWithSizeAbove(number: Long): List<Directory>{
        return  directories.filter { it.getSize() >= number } + directories.map { it.getDirectoriesWithSizeAbove(number) }.flatten()
    }
    fun getSize(): Long{
        return directories.sumOf { it.getSize() } + files.sumOf { it.size }
    }
}

data class File(val name: String, val size: Long)

