package code

// Lesson: easy depth traversal via recursion,
// Lesson: implicit ordering of file to speed it up?  If no order, impose order to make it easier on myself?

fun day12problem1(inputString: String): Any? {
    var alreadyCounted = mutableListOf<Int>()
    var lines = inputString.split("\n".toRegex())
    return getCount(lines, alreadyCounted,  0)
}

fun getCount(lines: List<String>, alreadyCounted: MutableList<Int>, index: Int): Int {
    if (alreadyCounted.contains(index)) {
        return 0
    } else {
        alreadyCounted.add(index)
    }

    val programIds = lines[index].split(" ".toRegex())

    var retVal = 1
    for (i in 2..programIds.size-1) { // GW comment: like a depths first search, but relies on the implicit ordering in the file to
        // jump to
        // that line
        retVal += getCount(lines, alreadyCounted, programIds[i].substringBefore(",").toInt())
    }
    return retVal
}

fun day12problem2(inputString: String): Any? {

    var alreadyCounted = mutableListOf<Int>()

    var totalGroups = 0
    var lines = inputString.split("\n".toRegex())
    for(i in 0..lines.size-1) {  // GW comment: brute force, "counting" through each line, but relying on the alreadyCounted to
        // avoid repeating extensive calculations
        if(getCount(lines, alreadyCounted, i) != 0){
            totalGroups += 1
        }
    }
    return totalGroups
}