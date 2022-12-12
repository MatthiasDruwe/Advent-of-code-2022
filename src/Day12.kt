import kotlin.math.min

fun main() {


    fun shortestPath(nodesArray: List<List<Node>>, startpoint: Char): Long {
        val nodes = nodesArray.flatten().toMutableList()
        val nodesDone = MutableList(0) { Node(0, 0, '0') }
        val nodeS = nodes.first { it.value == startpoint }
        nodeS.cost = 0
        nodes.remove(nodeS)
        nodesDone.add(nodeS)
        nodeS.cost = 0
        while (nodes.isNotEmpty()) {
            val currentNode = nodesDone.last()
            val nodeAbove = if (currentNode.x > 0) nodesArray[currentNode.x - 1][currentNode.y] else null
            val nodeLeft = if (currentNode.y > 0) nodesArray[currentNode.x][currentNode.y - 1] else null
            val nodeBelow =
                if (currentNode.x < nodesArray.size - 1) nodesArray[currentNode.x + 1][currentNode.y] else null
            val nodeRight =
                if (currentNode.y < nodesArray[0].size - 1) nodesArray[currentNode.x][currentNode.y + 1] else null

            val list = listOf(nodeAbove, nodeBelow, nodeLeft, nodeRight)

            list.filterNotNull().forEach { node ->
                val value = if (node.value == 'S') 'a' else if (node.value == 'E') 'z' else node.value
                val currentNodeValue =
                    if (currentNode.value == 'S') 'a' else if (currentNode.value == 'E') 'z' else currentNode.value


                if (value <= currentNodeValue + 1) {
                    node.cost = min(node.cost, currentNode.cost + 1)
                }

            }

            val leastCostNode = nodes.sortedBy { it.cost }.first()

            nodes.remove(leastCostNode)
            nodesDone.add(leastCostNode)
        }





        return nodesDone.first { it.value == 'E' }.cost
    }

    fun part1(input: List<String>): Long {
        val nodesArray =
            input.map { it.toCharArray() }.mapIndexed { i, row -> row.mapIndexed { j, value -> Node(i, j, value) } }
        return shortestPath(nodesArray, 'S')
    }

    fun part2(input: List<String>): Long {
        val nodesArray =
            input.map { it.toCharArray() }.mapIndexed { i, row -> row.mapIndexed { j, value -> Node(i, j, value) } }

        val nodes = nodesArray.flatten().toMutableList()
        val nodesDone = MutableList(0) { Node(0, 0, '0') }
        val nodeS = nodes.first { it.value == 'E' }
        nodeS.cost = 0
        nodes.remove(nodeS)
        nodesDone.add(nodeS)
        nodeS.cost = 0
        while (nodes.isNotEmpty()) {
            val currentNode = nodesDone.last()
            val nodeAbove = if (currentNode.x > 0) nodesArray[currentNode.x - 1][currentNode.y] else null
            val nodeLeft = if (currentNode.y > 0) nodesArray[currentNode.x][currentNode.y - 1] else null
            val nodeBelow =
                if (currentNode.x < nodesArray.size - 1) nodesArray[currentNode.x + 1][currentNode.y] else null
            val nodeRight =
                if (currentNode.y < nodesArray[0].size - 1) nodesArray[currentNode.x][currentNode.y + 1] else null

            val list = listOf(nodeAbove, nodeBelow, nodeLeft, nodeRight)

            list.filterNotNull().forEach { node ->
                val value = if (node.value == 'S') 'a' else if (node.value == 'E') 'z' else node.value
                val currentNodeValue =
                    if (currentNode.value == 'S') 'a' else if (currentNode.value == 'E') 'z' else currentNode.value


                if (value >= currentNodeValue - 1) {
                    node.cost = min(node.cost, currentNode.cost + 1)
                }

            }

            val leastCostNode = nodes.sortedBy { it.cost }.first()

            nodes.remove(leastCostNode)
            nodesDone.add(leastCostNode)
        }





        return nodesDone.filter { it.value == 'a' && it.cost > 0 }.minOf { it.cost }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_example")
    check(part1(testInput) == 31L)
    check(part2(testInput) == 29L)
    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}

data class Node(val x: Int, val y: Int, val value: Char, var cost: Long = Long.MAX_VALUE, var previous: Node? = null)
