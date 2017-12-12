import java.util.*

fun readAsStringList(fileName : String, separator : String): ArrayList<String> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split(separator)
    return ArrayList(input) // Lesson: inferred return type, no conversion required
}

fun readAsIntList(fileName : String, separator : String): ArrayList<Int> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split(separator).map{ it.toInt() }
    return ArrayList(input)
}

fun readLineByLine(fileName: String): Scanner {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/" + fileName))
    return scan
}

fun readString(fileName: String): String {
    return ClassLoader.getSystemResource("resources/" + fileName).readText().trim()
}

// TODO:
// Practice iterating through collections efficiently
// Day 10 Reverse sublist function
fun MutableList<Int>.reverse(index1: Int, index2: Int, swaps: Int) { // Lesson: Extension class for ArrayList for custom reverse
    var numSwaps = swaps / 2
    var start = index1
    var end = index2
    while (numSwaps > 0) {
        swap(start, end)
        start = (start + 1) % this.size
        end = (end - 1 + size) % size // Lesson: apparently mods can be negative
        numSwaps--
    }
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * Pre-condition: toVisit is populated with the first item to visit
 * Each visited node has an action performed upon it
 * Post-Condition: includes has all of the elements visited
 */
fun <T> breadthFirstVisit(toVisit: LinkedList<T>, includes: HashSet<T>, adjList: HashMap<T, ArrayList<T>>, action: (T) -> Unit ) {
    while (!toVisit.isEmpty()) {
        val visited = toVisit.poll()
        includes.add(visited)
        action(visited)
        val neighbors = adjList.get(visited)!!
        for (n in neighbors) {
            if (!includes.contains(n)) {
                toVisit.add(n)
            }
        }
    }
}