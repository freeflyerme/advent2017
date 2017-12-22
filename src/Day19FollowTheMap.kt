fun main(args: Array<String>) {
    part19_1()
}

fun part19_1() {  // Part1: LIWQYKMRP // Part2: 16764
    val lines = readString("Day19.txt").split("\n")
    val ROWS = lines.size
    val COLUMNS = lines.map { it.length }.max()!!

    val grid = ArrayList<ArrayList<String>>()
    for (line in lines) {
        val aRow = ArrayList<String>()
        for (c in line) {
            aRow.add(c.toString())
        }
        grid.add(aRow)
    }

    grid.reverse()
    // getStartPosition
    var direction = DIRECTION.DOWN
    val startX = grid.last().indexOf("|")
    val startY = grid.size

    var x = startX
    var y = startY

    var visited = mutableListOf<String>()
    val movementStrings = mutableListOf<String>("|", "-", "+")

    var steps = 0 // count the steps for part 2
    loop@while(true) {
        var newCoord = move(direction, x, y)
        x = newCoord.first
        y = newCoord.second
        val currentString = grid[y][x]

        if (currentString == " " || x < 0 || x > COLUMNS || y < 0 || y > ROWS) {
            println("At the end! (x, y) = (${x}, ${y})")
            break@loop
        }

        if (!movementStrings.contains(currentString) ) {
            visited.add(currentString)
        }

        if (currentString == "+") {
            direction = changeDirection(x, y, direction, grid)
        }
        steps++
    }

    println("Visited: $visited") // Lesson: String interpolation
    visited.forEach{ print(it) }
    println("steps: $steps")
}

private fun changeDirection(x: Int, y: Int, direction: DIRECTION, grid: ArrayList<ArrayList<String>>): DIRECTION {
    val dirsToCheck = DIRECTION.values().filter { it != direction && it != getOppositeDirection(direction) } // also need to remove opposite

    for (dir in dirsToCheck) {
        val newY = y + dir.dy
        val newX = x + dir.dx
        if (newX < 0 || newY < 0 || newY > grid.size || newX > grid.get(newY).size) {
            continue
        } else if (grid[newY][newX] != " ") {
            return dir
        } else { // not a direction to go
        }
    }
    return direction
}

private fun move(direction: DIRECTION, x: Int, y: Int): Pair<Int, Int> {
    return Pair(x + direction.dx, y + direction.dy)
}