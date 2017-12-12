import java.util.*

fun main(args: Array<String>) {
    part12_2()
}

fun part12_2() {
    val scanner = readLineByLine("Day12.txt")
    // adj list
    val adjList = HashMap<String, ArrayList<String>>()
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine().replace(',', ' ').replace("  ", " ") // ugly, but works
        val split = line.split(" ")
        val neighbors = ArrayList<String>()
        for (i in 2 until split.size) {
            neighbors.add(split[i])
        }
        adjList.put(split[0], neighbors)
    }

    val includes = HashSet<String>()
    val toVisit = LinkedList<String>()

    // Lesson: when copying and pasting stuff, be sure to mind what previous states were copied
    var numGroups = 0
    val leftToVisit = HashSet<String>()
    leftToVisit.addAll(adjList.keys)



    while (leftToVisit.isNotEmpty()) {
        toVisit.offer(leftToVisit.first())
        visitFromNode(toVisit, includes, adjList)

        // all the visited nodes are in includes
        println(includes)
        leftToVisit.removeAll(includes)
        includes.clear()
        toVisit.clear()
        numGroups++
    }

    println(numGroups)

}

private fun visitFromNode(toVisit: LinkedList<String>, includes: HashSet<String>, adjList: HashMap<String, ArrayList<String>>) {
    while (!toVisit.isEmpty()) {
        val visited = toVisit.poll()
        includes.add(visited)
        val neighbors = adjList.get(visited)!!
        for (n in neighbors) {
            if (!includes.contains(n)) {
                toVisit.add(n)
            }
        }
    }
}

fun part12_1() {
    val scanner = readLineByLine("Day12.txt")
    // adj list
    val adjList = HashMap<String, ArrayList<String>>()
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine().replace(',', ' ').replace("  ", " ") // ugly, but works
        val split = line.split(" ")
        val neighbors = ArrayList<String>()
        for (i in 2 until split.size) {
            neighbors.add(split[i])
        }
        adjList.put(split[0], neighbors)
    }

    println(adjList)

    val includes = HashSet<String>()
    val toVisit = LinkedList<String>()
    toVisit.offer("0")

    while(!toVisit.isEmpty()) {
        val visited = toVisit.poll()
        includes.add(visited)
        val neighbors = adjList.get(visited) !!
        for (n in neighbors) {
            if (!includes.contains(n)) {
                toVisit.add(n)
            }
        }
    }

    println(includes.size)

}

// breadth search starting from 0

//fun changeablePart() {
//
//}