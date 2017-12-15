fun main(args: Array<String>) {
    part15_2()
}

// lazily evaluated stream generator for both, then compare?

fun part15_2() { // 316
    val nextA = 16807L // Lesson: use Long, for values over ~2 billion
    val nextB = 48271L
    val divide = 2147483647L

    val seedA = 618L // 618L // 65L test
    val seedB = 814L // 814L // 8921L test

    var countA = seedA
    var countB = seedB
    var same = 0
    var end = 5000000
    val two_sixteenth = 65536
    for (i in 1..end) {
        countA = generateNext(countA, nextA, divide, 4)
        countB = generateNext(countB, nextB, divide, 8)
        if (Integer.toBinaryString((countA % two_sixteenth).toInt()) == Integer.toBinaryString((countB % two_sixteenth).toInt())) {
            same++
        } // Lesson: Integer to Binary library

    }
    println(same)
}

fun generateNext(current: Long, multiple: Long, divide: Long, modReq: Long): Long {
    val nextVal = (current * multiple) % divide
    return if ((nextVal % modReq) != 0L) {
        generateNext(nextVal, multiple, divide, modReq)
    } else {
        nextVal
    }
}

fun part15_1() { // 577
    val nextA = 16807L
    val nextB = 48271L
    val divide = 2147483647L

    val seedA = 65L // 618L // 65L test
    val seedB = 8921L // 814L // 8921L test

    var countA = seedA
    var countB = seedB
    var same = 0
    var end = 40000000
    var two_sixteenth = 65536
    for (i in 1..end) {
        countA = (countA * nextA) % divide
        countB = (countB * nextB) % divide
        if (Integer.toBinaryString((countA % two_sixteenth).toInt()) == Integer.toBinaryString((countB % two_sixteenth).toInt())) {
            same++
        }

    }
    println(same)
}

//fun changeablePart() {
//
//}
