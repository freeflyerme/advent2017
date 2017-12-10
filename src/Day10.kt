fun main(args: Array<String>) {
    part10_2()
}

fun part10_2() {
    val problemInput = readString("Day10p.txt")

    var intArray = mutableListOf<Int>()
    for (c in problemInput.toCharArray()) {
        intArray.add(c.toInt())
    }
    intArray.addAll(listOf(17, 31, 73, 47, 23))
    println(intArray)

//    var skip = 0
//
//    var input = mutableListOf<Int>() // change 5 to 255 later
//    for (i in 0..255) {
//        input.add(i)
//    }
//
//    var start = 0
//    var end = 0
//    for (aLen in lengthLists) {
//        end = (start + aLen - 1) % input.size
//        input.reverse(start, end, aLen)
//        start = (end + skip + 1) % input.size
//        skip++
//    }
//
//    println(input)
//    println(input[0] * input[1])

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

fun MutableList<Int>.reverse(index1: Int, index2: Int, swaps: Int) {
    var numSwaps = swaps / 2
    var start = index1
    var end = index2
    while (numSwaps > 0) {
//        println("Start:" + start + " " + end + " " + numSwaps)
        swap(start, end)
        start = (start + 1) % size
        end = (end - 1 + size) % size
        numSwaps--
    }
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}