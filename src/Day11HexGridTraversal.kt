import kotlin.math.abs

fun main(args: Array<String>) {
    part11_1()
}

fun part11_1() {
    val moves = readAsStringList("Day11.txt", ",")
    var x = 0
    var y = 0
    var maxDist = 0
    var numMoves: Int
    for (aMove in moves) {
        var result = move(aMove)
        x += result.first
        y += result.second
        numMoves = calculateDist(y, x)
        if (maxDist < numMoves) {
            maxDist = numMoves
        }
    }

    println("x:"+x+", y:"+y)
    println(maxDist) // Part 2 is max distance

}

private fun calculateDist(y: Int, x: Int): Int {
    // Lesson: have a formulated plan before submitting -- exponentially worse backoff time
    // Problem particular: in a Hex grid, a move in diagonal is only "half" a move up / down
    var numMoves = 0
    var ySpacesLeft : Int
    if (abs(y) > abs(x)) {
        numMoves += abs(x)
        ySpacesLeft = abs(y) - abs(x)
        numMoves += ySpacesLeft / 2
    } else {
        numMoves = abs(x)
    }
    return numMoves
}

fun move(dir: String): Pair<Int, Int> {
    when (dir) {
        "n" -> return Pair(0,2)
        "ne" -> return Pair(1,1)
        "se" -> return Pair(1,-1)
        "s" -> return Pair(0,-2)
        "sw" -> return Pair(-1,-1)
        "nw" -> return Pair(-1,1)
    }
    return Pair(0,0)
}