fun main(args: Array<String>) {
    part10_2()
}
fun test() { // repeatXor does its job!
//    65 ^ 27 ^ 9 ^ 1 ^ 4 ^ 3 ^ 40 ^ 50 ^ 91 ^ 7 ^ 6 ^ 0 ^ 2 ^ 5 ^ 68 ^ 22
    // Xor is associative (any order executed in sequence) and commutative (swap left and right)
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
    for (loop in 0 until 64) {
        for (aLen in intArray) { // Lesson: Kotlin passes same as Java -- reference for ref objects, value for primitives
            end = (start + aLen - 1) % input.size
            input.reverse(start, end, aLen)
            start = (end + skip + 1) % input.size
            skip++
        }
    }
    // 64 rounds later... intArray is "sparse hash" as called by the problem

    // firstHashImpl(input) // break up list by 16's, then iterate through them
    secondHashImpl(input)

}

private fun secondHashImpl(input: MutableList<Int>) {
    // println(input) // debug
    val groupBy = input.withIndex().groupBy { it.index / 16 } // Lesson: get index for any array
    val map = groupBy.map { entry -> entry.value.map { it.value } } // Lesson: how to go from a map with index, to just the values
    // println(map) // debug

    // Lesson: fold, also lock down first parameter syntax (currying).  Fold right is like fold, but starts last and goes left
    val hash = map.map { list -> list.fold(0) { total, next -> total.xor(next) } }

    hash.forEach { decNumber ->
        var hex = Integer.toHexString(decNumber)
        print(if (hex.length == 1) "0" + hex else hex) // Lesson: no Ternary operator in Kotlin
    }
}

private fun firstHashImpl(input: MutableList<Int>) {
    var denseHash = mutableListOf<Int>()
    for (i in 0 until 16) {

        denseHash.add(repeatXor(input, i * 16, (i * 16 + 15)))
    }

    // 2f8c3d2100fdd57cec130d928b0fd2dd -- Day10.txt
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