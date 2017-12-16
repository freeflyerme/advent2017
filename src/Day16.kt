import java.util.*

fun main(args: Array<String>) {
//    part16_1()
    part16_2()
}

val PROGRAM_SIZE = 16 // number of characters in the string

fun part16_2() {
    val danceMoves = readAsStringList("Day16.txt", ",")

    var programs = mutableListOf<Char>()
    for (i in 0 until PROGRAM_SIZE) { // 97 is a in ascii, P is 16th character
        programs.add((97 + i).toChar())
    }

    // can do this more efficiently, with an offset for spin, to avoid entire array moves
    var spinOffset = 0
    // dance 1 billion times!!!
    // 2 possible solutions: 1 permutation groups are cyclical -- find the cyclic, then calculate results
    // 2: calculate permutation shortcut, repeat until down
    // let's try 1 -- 1 is much easier computational wise.  Turned out the cyclic group is only sized 36 here
    var seenPermutations = ArrayList<List<Char>>()
    seenPermutations.add(ArrayList(programs))

    loop@while(true) {
        spinOffset = danceOnce(danceMoves, spinOffset, programs)  // Lesson: mutable lists in other data -- need to store immutable
        val currentResult = getResultWithOffset(spinOffset, programs)
        if (seenPermutations.contains(currentResult)) {
            // println("found! " + seenPermutations.size) // debug
            break@loop
        } else {
            seenPermutations.add(ArrayList(currentResult))
        }

    }

    println(seenPermutations.size) // 8, so the 9th one cycles back

    // calculate the last ones left
    val remainder = 1000000000 % (seenPermutations.size) // 1000000000
    val finalDanceResults = seenPermutations[remainder]

    for (c in finalDanceResults) {
        print(c)
    }
}

private fun getResultWithOffset(offset: Int, programs: List<Char>): List<Char> {
    var result = mutableListOf<Char>()
    for (i in  0 until PROGRAM_SIZE) {
        result.add(programs[(i+offset)%PROGRAM_SIZE])
    }
    return result
}

fun part16_1() { // bijankplfgmeodhc
    val danceMoves = readAsStringList("Day16.txt", ",")

    var programs = mutableListOf<Char>()
    for (i in 0 until PROGRAM_SIZE) { // 97 is a in ascii, P is 16th character
        programs.add((97 + i).toChar())
    }

    // can do this more efficiently, with an offset for spin, to avoid entire array moves
    var spinOffset = 0
    spinOffset = danceOnce(danceMoves, spinOffset, programs)

    printResults(programs, spinOffset)

}

private fun printResults(programs: MutableList<Char>, spinOffset: Int) {
    for (i in 0 until PROGRAM_SIZE) {
        print(programs[(i + spinOffset) % PROGRAM_SIZE])
    }
}

private fun danceOnce(danceMoves: ArrayList<String>, spinOffset: Int, programs: MutableList<Char>): Int {
    var spinOffset1 = spinOffset
    for (move in danceMoves) {
        val type = move[0]
        if (type == 's') {
            // spin
            spinOffset1 = spin(spinOffset1, move.substringAfter(type).toInt())
        } else if (type == 'x') {
            // swap positions
            val split = move.substringAfter(type).split('/')
            val pos1 = (spinOffset1 + split[0].toInt()) % (PROGRAM_SIZE)
            val pos2 = (spinOffset1 + split[1].toInt()) % (PROGRAM_SIZE)
            programs.swap(pos1, pos2)
        } else { // p, swap program by name
            val split = move.substringAfter(type).split('/')
            val pos1 = programs.withIndex().find { it.value.toString() == split[0] }?.index!!
            val pos2 = programs.withIndex().find { it.value.toString() == split[1] }?.index!!
            programs.swap(pos1, pos2)
        }
    }
    return spinOffset1
}

private fun spin(currentOffset: Int, additionalOffset: Int): Int {
    return (currentOffset - additionalOffset + PROGRAM_SIZE) % PROGRAM_SIZE
}

//fun changeablePart() {
//
//}