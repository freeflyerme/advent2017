fun main(args: Array<String>) {
    part22_2()
}

fun part22_2() {
    // input grid is 25 x 25
    val input = readString("Day22.txt")

    val MAX_SIZE = 6000
    val GRID_MID = MAX_SIZE / 2

    var grid = Array(MAX_SIZE, { Array(MAX_SIZE, {0}) }) // a 200 x 200 2d array
    val readToGrid = readToGrid(input, 2)

    val halfInputSize = readToGrid.size / 2

    setSquareInGrid(GRID_MID - halfInputSize, GRID_MID - halfInputSize, readToGrid, grid)

//    printGrid(grid)

    var x = GRID_MID
    var y = GRID_MID
    var currentDir = DIRECTION.UP

    var infectionCount = 0

    for (i in 1..10000000) {
        /**
         * 0 clean -- left, weakened
         * 1 weakened -- continue moving, infected
         * 2 infected -- right, flagged
         * 3 flagged -- reverse, clean
         */
//        println("Step: $i")
        if (grid[y][x] == 0) {
            currentDir = turnLeft(currentDir)
            grid[y][x] = 1
        } else if (grid[y][x] == 1) {
            // no change
            grid[y][x] = 2
            infectionCount++
        } else if (grid[y][x] == 2) {
            currentDir = turnRight(currentDir)
            grid[y][x] = 3
        } else {
            currentDir = getOppositeDirection(currentDir)
            grid[y][x] = 0
        }

        x += currentDir.dx
        y += (-1 * currentDir.dy)
//        println("$y, $x dir: $currentDir, val: ${grid[y][x]}")
//        printGrid(grid, x, y)

    }
    println("Finished")
//    printGrid(grid)
    println(grid.fold(0){ total, next -> total + next.sum() })
    println("Infects: $infectionCount")

}

fun part22_1() {
    // input grid is 25 x 25
    val input = readString("Day22.txt")

    val MAX_SIZE = 600
    val GRID_MID = MAX_SIZE / 2

    var grid = Array(MAX_SIZE, { Array(MAX_SIZE, {0}) }) // a 200 x 200 2d array
    val readToGrid = readToGrid(input)

    val halfInputSize = readToGrid.size / 2

    setSquareInGrid(GRID_MID - halfInputSize, GRID_MID - halfInputSize, readToGrid, grid)

//    printGrid(grid)


    var x = GRID_MID
    var y = GRID_MID
    var currentDir = DIRECTION.UP

    var infectionCount = 0

    for (i in 1..10000) {
//        println("Step: $i")
        if (grid[y][x] == 1) {
            currentDir = turnRight(currentDir)
            grid[y][x] = 0
        } else {
            currentDir = turnLeft(currentDir)
            grid[y][x] = 1
            infectionCount++
        }

        x += currentDir.dx
        y += (-1 * currentDir.dy)
//        println("$y, $x dir: $currentDir, val: ${grid[y][x]}")
//        printGrid(grid, x, y)

    }
    println("Finished")
//    printGrid(grid)
    println(grid.fold(0){ total, next -> total + next.sum() })
    println("Infects: $infectionCount")

}

fun printGrid(input : Array<Array<Int>>, x: Int = -1, y: Int = -1) {
    for (i in 0 until input.size) {
        for (j in 0 until input.size) {
            if (i == y && j == x) {
                print("${input[i][j]}* ")
            } else {
                print("${input[i][j]} ")
            }
        }
        print("\n")
    }

}