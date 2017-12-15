fun main(args: Array<String>) {
    part14_1()
    part14_2()
}

fun part14_2() {

    val input = "vbqugkhl" // "vbqugkhl" // test: flqrgnkx -- 1242

    var inputWithNum = ArrayList<String>()
    for (i in 0 .. 127) {
        inputWithNum.add(input + "-" + i)
    }


    var grid = ArrayList<ArrayList<Int>>()

    for (i in 0 .. 127) {
        val hashResult = fullKnotHash(inputWithNum[i])
        val temp = hashResult.toCharArray().map { hexToBin(it.toString()) !! }
        var row = ArrayList<Int>()
        for (e in temp) {
            for (c in e) {
                if (c == '1') {
                    row.add(1)
                } else {
                    row.add(0)
                }

            }
        }
        grid.add(row)

    }

    // find the groups
    // find a new start, find all neighbors, set to be zero,
    var group = 0
    var newGroupStart = findNewGroup(grid)
    while (newGroupStart.first != -1) {
        group++
        removeNeighbors(grid, newGroupStart.first, newGroupStart.second)
        newGroupStart = findNewGroup(grid)
    }
    println(group)
}

private fun findNewGroup(grid: ArrayList<ArrayList<Int>>): Pair<Int, Int> {
    var x = -1
    var y = -1
    for (i in 0 until 128) {
        for (j in 0 until 128) {
            if (grid[i][j] == 1) {
                x = i
                y = j
                return Pair(x, y)
            }
        }
    }
    return Pair(x, y)
}

fun removeNeighbors(grid: ArrayList<ArrayList<Int>>, x: Int, y:Int) {
    // find neighbors, expand to remove
    grid[x][y] = 0
    // check in a cross around the point
    if (x > 0) {
        if (grid[x-1][y] == 1) {
            removeNeighbors(grid, x-1, y)
        }
    }
    if (x < 127) {
        if (grid[x+1][y] == 1) {
            removeNeighbors(grid, x+1, y)
        }
    }
    if (y > 0) {
        if (grid[x][y-1] == 1) {
            removeNeighbors(grid, x, y-1)
        }
    }
    if (y < 127) {
        if (grid[x][y+1] == 1) {
            removeNeighbors(grid, x, y+1)
        }
    }

}

fun part14_1() {

    val input = "vbqugkhl"

    var inputWithNum = ArrayList<String>()
    for (i in 0 .. 127) {
        inputWithNum.add(input + "-" + i)
    }

    var grid = ""
    var count = 0
    for (i in 0 .. 127) {
        val hashResult = fullKnotHash(inputWithNum[i])
        val temp = hashResult.toCharArray().map { hexToBin(it.toString()) !! }
//        println(temp)
        temp.forEach { s ->
            s.forEach { c -> if (c == '1') count++ }
        }
        grid += temp
        grid += "\n"
    }

//    println(grid)

    println(count)


}



//fun changeablePart() {
//
//}