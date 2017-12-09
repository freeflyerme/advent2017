fun main(args: Array<String>) {
    part7_2()
}

data class Node constructor (val name: String, val weight:Int, var treeWeight: Int = 0) // lesson: data class

fun part7_2() { // TODO: solve this better programmatically
    // result: 1060

    // replace Adjacency List with a tree structure, but also have a way to access the nodes by name?

    val adjList = HashMap<Node, ArrayList<Node>>()

    // do 2 passes -- first one creates all of the nodes
    // 2nd pass -- create the parents for the nodes
    // root: ykpsek -- part 1 solution
    extractNodes(adjList)

    populateNodeParents(adjList)

    updateTreeWeight("ykpsek", adjList)

    // adjList.keys.find { it.name.equals("ykpsek") } // debug

    findImbalance("ykpsek", adjList)
}

fun findImbalance(nodeName: String, adjList: HashMap<Node, ArrayList<Node>>) {
    val node = adjList.keys.find { it.name.equals(nodeName) } !!

    var wrongCandidate1: Node
    var wrongCandidate2: Node
    for (child in adjList.get(node) ?: ArrayList<Node>()) {
        updateTreeWeight(child.name, adjList)
    }
}

fun updateTreeWeight(nodeName: String, adjList: HashMap<Node, ArrayList<Node>>) {
    // for each node, the weight is that of the sum of the children, plus itself
    val node = adjList.keys.find { it.name.equals(nodeName) } !!

    for (child in adjList.get(node) ?: ArrayList<Node>()) {
        updateTreeWeight(child.name, adjList)
    }

    node.treeWeight = node.weight + adjList.get(node)?.sumBy { it.treeWeight } !!
}

fun populateNodeParents(adjList: HashMap<Node, ArrayList<Node>>) {
    val scanner = readLineByLine("Day7.txt")
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        val split = line.split(" ")

        var node: Node
        if (split.size > 3) {
            val nodeName = split[0]
            val nodeWeight = split[1].substring(1, split[1].length - 1).toInt() // strip parenthesis
            node = Node(nodeName, nodeWeight)

            val children = ArrayList<String>()
            for (i in 3 until split.size) {
                if (split[i].last().equals(',')) {
                    children.add(split[i].substring(0, split[i].length - 1))
                } else {
                    children.add(split[i])
                }
            }

            // for each element, add in their parent
            for (child in children) {
                val find = adjList.keys.find { child.equals(it.name) } // question: how does find syntax work and
                // the ?
                adjList.get(node)?.add(find!!)
            }
        }

    }
}

fun extractNodes(adjList: HashMap<Node, ArrayList<Node>>) {
    val scanner = readLineByLine("Day7.txt")
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        val split = line.split(" ")

        // extract the elements
        val nodeName = split[0]
        val nodeWeight = split[1].substring(1, split[1].length - 1).toInt() // strip parenthesis
        val node = Node(nodeName, nodeWeight)
        adjList.put(node, ArrayList<Node>())
    }
}

fun part7_1() {
    val allElementsAndParent = HashMap<String, String>()
    val allElements = HashSet<String>()

    val scanner = readLineByLine("Day7.txt")
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        val split = line.split(" ")

        // extract the elements
        val nodeParent = split[0]
        val nodeWeight = split[1] // strip parenthesis
        val children = ArrayList<String>()
        if (split.size > 3) {
            for (i in 3 until split.size) {
                if (split[i].last().equals(',')) {
                    children.add(split[i].substring(0, split[i].length - 1))
                } else {
                    children.add(split[i])
                }
            }
        }

        // for each element, add in their parent
        for (child in children) {
            allElementsAndParent.put(child, nodeParent)
            allElements.add(child)
        }

        allElements.add(nodeParent)
    }

    // return the node without a parent
    val iterator = allElements.iterator()
    while (iterator.hasNext()) {
        if (allElementsAndParent.containsKey(iterator.next())) {
            iterator.remove()
        }
    }

    println(allElements) // what's left should be just the element, no parent -- root

}


// read in all of the elements -> store their parents
// find out which element has not a parent

//fun changeablePart() {
//
//}