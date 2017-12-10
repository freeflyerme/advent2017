fun main(args: Array<String>) {
    part10_2()
}
fun test() { // repeatXor does its job!
//    65 ^ 27 ^ 9 ^ 1 ^ 4 ^ 3 ^ 40 ^ 50 ^ 91 ^ 7 ^ 6 ^ 0 ^ 2 ^ 5 ^ 68 ^ 22
    var testInput = listOf(65,27,9,1,4,3,40,50,91,7,6,0,2,5,68,22)
    println(repeatXor(testInput,0,15)) // 64
}

fun part10_2() {
    val problemInput = readString("Day10.txt")

    // Thought: Summarize the problem, cut down on words
    // Basically, add some arbitrary sequence to the given lengths
    // Then, do what you did before, but 64 rounds, preserving start and skip numbers between rounds
    // Then hash 16 blocks, then print them out in Hex

    var intArray = mutableListOf<Int>()
    for (c in problemInput.toCharArray()) {
        intArray.add(c.toInt())
    }
    intArray.addAll(listOf(17, 31, 73, 47, 23))


    var input = mutableListOf<Int>()
    for (i in 0..255) {
        input.add(i)
    }

    var start = 0
    var end: Int
    var skip = 0
    // one round of hashing
    for (loop in 0 until 64) {
        for (aLen in intArray) {
            end = (start + aLen - 1) % input.size
            input.reverse(start, end, aLen)
            start = (end + skip + 1) % input.size
            skip++
        }
    }

    // 64 rounds later... intArray is sparseHash
    println(input)

    var denseHash = ArrayList<Int>()
    for(i in 0 until 16) {
        denseHash.add(repeatXor(input, i*16, (i*16 + 15)))
    }

    // 3efbe78a8d82f2997931a4aa0b16a9d -- AoC 2017
    for (i in denseHash) {
        var print = Integer.toHexString(i) // Lesson: toHexString doesn't have preceding 0's.  Also, how to convert to Hex
        if (print.length == 1) {
            print = "0" + print
        }
        print(print)
    }

}

fun repeatXor(array : List<Int>, start: Int, end: Int): Int {
    var result = array[start]
    for(i in (start+1)..end) {
        result = result.xor(array[i])
    }
    return result
}

fun part10_1() {
    val lengthLists = readAsIntList("Day10.txt", ",")
    var skip = 0

    var input = mutableListOf<Int>() // change 5 to 255 later
    for (i in 0..255) {
        input.add(i)
    }

    var start = 0
    var end = 0
    for (aLen in lengthLists) {
        end = (start + aLen - 1) % input.size
        input.reverse(start, end, aLen)
        start = (end + skip + 1) % input.size
        skip++
    }

    println(input)
    println(input[0] * input[1])

}

fun MutableList<Int>.reverse(index1: Int, index2: Int, swaps: Int) { // Lesson: Extension class for ArrayList for custom reverse
    var numSwaps = swaps / 2
    var start = index1
    var end = index2
    while (numSwaps > 0) {
        swap(start, end)
        start = (start + 1) % size
        end = (end - 1 + size) % size // Lesson: apparently mods can be negative
        numSwaps--
    }
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}