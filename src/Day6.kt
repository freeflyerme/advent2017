fun main(args: Array<String>) {
    part6_1()
}

fun part6_1() {

    var inList = readAsIntList("Day6.txt")

    val configSeen = HashMap<ArrayList<Int>, Int>()
    var counter = 0
    while (!configSeen.containsKey(inList)) {
        configSeen.put(inList, counter)
        val highestIndex = getHighestIndex(inList)
        redist(highestIndex, inList)
        // println(inList) // debug
        counter++
    }

    // part 2 is keeping track of the indexes -- changed the configSeen to HashMap from HashSet
    println("counter:"+counter)
    println("value:"+(counter - configSeen.get(inList) !!))  // Lesson: !! the Int?
}

fun getHighestIndex(input: ArrayList<Int>) : Int{
    var highest = -1
    var highestIndex = 0
    for (i in 0 until input.size) {
        if (input[i] > highest) {
            highest = input[i]
            highestIndex = i
        }
    }
    return highestIndex
}

fun redist(index: Int, array: ArrayList<Int>) {
    val size = array.size
    var numToDist = array[index]
    array[index] = 0
    var i = 1
    while (numToDist > 0) {
        array[(index + i) % size]++
        i++
        numToDist--
    }
}