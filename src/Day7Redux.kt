fun main(args: Array<String>) {
    part7_1Redux()
}

fun part7_1Redux() {
    val parentChild = HashMap<String, ArrayList<String>>()
    readAsStringList("Day7.txt", "\n").fold(parentChild) {
        result, line ->
        val split = line.replace(",", "").split(" ")
        if (split.size == 2) {
            result.put(split[0], ArrayList())
        } else {
            result.put(split[0], ArrayList(split.subList(3, split.size-1)))
        }
        result // implicit return
    }

    val root = getRoot(parentChild)
    println(root)
}

fun getRoot(parentChild: HashMap<String, ArrayList<String>>): String {
    val parents = parentChild.keys
    var aNode = parents.first()
    loop@while (true) {
        val keys = parentChild.filter { it.value.contains(aNode) }.keys
        if (keys.isNotEmpty()) {
            aNode = keys.first()
        } else {
            break@loop
        }

    }
    return aNode
}
