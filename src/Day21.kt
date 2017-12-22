fun main(args: Array<String>) {
    part21_1()
}

fun part21_1() {

    var convertMap = readInputPatterns()

    /** Input pattern
    .#.
    ..#
    ###
     */
    // start pattern internalized
    var currentPattern = convertToArray(".#./..#/###")

    // at each iteration, divided as needed, look up new results
    // combine results together
    for (i in 1..18) {
        val division = getDivision(currentPattern)
        var nextPattern = createNextGrid(currentPattern)

        for (x in 0 until currentPattern.size step division) {
            for (y in 0 until currentPattern.size step division) {
                var aSquare = getSquare(x, y, division, currentPattern)
                var newSquare = convertMap.get(aSquare)
                if (newSquare == null) {
                    println("Error: $x, $y, $aSquare")
                    newSquare = ArrayList()
                }
                setSquare(newCoord(x, division), newCoord(y, division), newSquare, nextPattern)
            }
        }

        currentPattern = nextPattern
    }

//    println(currentPattern)
    val count = currentPattern.fold(0){ total, row -> total + row.sum() }
    println(count)

}


fun readInputPatterns(): HashMap<ArrayList<ArrayList<Int>>, ArrayList<ArrayList<Int>>> {
    var hashMap = HashMap<ArrayList<ArrayList<Int>>, ArrayList<ArrayList<Int>>>()
    val input = readString("Day21.txt").split("\n")

    for (line in input) {
        val split = line.replace(" => ", " ").split(" ")
        val key = convertToArray(split[0])
        val value = convertToArray(split[1])
        hashMap.put(key, value)
        // flip key
        hashMap.put(flipHorizontal(key), value)
        hashMap.put(flipVertical(key), value)
        // rotate key
        val rot1 = rotateClockwise(key)
        hashMap.put(rot1, value)
        val rot2 = rotateClockwise(rot1)
        hashMap.put(rot2, value)
        val rot3 = rotateClockwise(rot2)
        hashMap.put(rot3, value)

        hashMap.put(flipVertical(rot1), value)
        hashMap.put(flipHorizontal(rot1), value)
        hashMap.put(flipVertical(rot2), value)
        hashMap.put(flipHorizontal(rot2), value)
        hashMap.put(flipVertical(rot3), value)
        hashMap.put(flipHorizontal(rot3), value)

    }
    return hashMap
}

private fun setSquare(x : Int, y: Int, input: ArrayList<ArrayList<Int>>, grid: ArrayList<ArrayList<Int>>) {
    for (i in 0 until input.size) {
        for (j in 0 until input.size) {
            grid[x+i][y+j] = input[i][j]
        }
    }
}

private fun getSquare(x : Int, y: Int, division: Int, grid: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    var output = ArrayList<ArrayList<Int>>()
    for (i in x until (x+division)) {
        var aRow = ArrayList<Int>()
        for (j in y until (y+division)) {
            aRow.add(grid[i][j])
        }
        output.add(aRow)
    }
    return output
}

private fun newCoord(input: Int, division: Int): Int {
    if (division == 2) {
        return (input / 2) * 3
    } else {
        return (input / 3) * 4
    }
}

private fun createNextGrid(input: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val output = ArrayList<ArrayList<Int>>()
    val newSize = if (input.size % 2 == 0) (input.size / 2) * 3 else (input.size / 3) * 4

    for (i in 1..newSize) {
        var aRow = ArrayList<Int>()
        for (i in 1..newSize) {
            aRow.add(0)
        }
        output.add(aRow)
    }
    return output
}

private fun getDivision(input: ArrayList<ArrayList<Int>>): Int {
    if (input.size % 2 == 0) {
        return 2
    }
    return 3
}

private fun rotateClockwise(input: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>>  {
    var mat = ArrayList<ArrayList<Int>>()
    val size = input.size
    for (i in 0 until size) {
        var aRow = ArrayList<Int>()
        for (j in 0 until size) {
            aRow.add(input[i][j])
        }
        mat.add(aRow)
    }

    val N = input.size

//    for (i in 0 until size / 2) {
//        for (j in i until size - i - 1) {
//            val temp = output[i][j]
//            output[i][j] = output[j][size - 1 - i]
//
//            output[j][size - 1 - i] = output[size - 1 - i][size - 1 - j]
//
//            output[size - 1 - i][size - 1 - j] = output[size - 1 - j][i]
//
//            output[size - 1 - j][i] = temp
//        }
//    }

    for (x in 0 until N / 2) {
        // Consider elements in group of 4 in
        // current square
        for (y in x until N.toInt() - x - 1) {
            // store current cell in temp variable
            val temp = mat[x][y]

            // move values from right to top
            mat[x][y] = mat[y][N.toInt() - 1 - x]

            // move values from bottom to right
            mat[y][N.toInt() - 1 - x] = mat[N.toInt() - 1 - x][N.toInt() - 1 - y]

            // move values from left to bottom
            mat[N.toInt() - 1 - x][N.toInt() - 1 - y] = mat[N.toInt() - 1 - y][x]

            // assign temp to left
            mat[N.toInt() - 1 - y][x] = temp
        }
    }
    return mat
}

private fun flipHorizontal(input: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val size = input.size
    var output = ArrayList<ArrayList<Int>>()
    for (i in 1 .. size) {
        output.add(input[size - i])
    }
    return output
}

private fun flipVertical(input: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val size = input.size
    var output = ArrayList<ArrayList<Int>>()
    for (i in 0 until size) {
        var aRow = ArrayList<Int>()
        for (j in 1..size) {
            aRow.add(input[i][size - j])
        }
        output.add(aRow)
    }
    return output
}

private fun convertToArray(input: String): ArrayList<ArrayList<Int>>{
    val split = input.split("/")
    var result = ArrayList<ArrayList<Int>>()
    for (line in split) {
        var row = ArrayList<Int>()
        for (c in line) {
            if (c == '#') {
                row.add(1)
            } else {
                row.add(0)
            }
        }
        result.add(row)
    }
    return result
}