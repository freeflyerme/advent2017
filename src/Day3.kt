fun main(args: Array<String>) {
    part3_2()
}

fun part3_2() {

    val end =  361527
    val startX = 100
    val startY = 100
    var x = startX
    var y = startY
    val board = Array(200, { IntArray(200) })

    board[startX][startY] = 1
    var i = 1
    find@ while (i < end) {

        // out of stuff, move _right_ once
        x = x + 1
        i = getNextNum2(board, x, y)
        if (i > end) {
            break@find
        }
        board[x][y] = i
        while (board[x][y + 1] != 0) { // move right, check up
            x = x + 1
            i = getNextNum2(board, x, y) // replace with adjacent sum function for part2
            board[x][y] = i
            if (i > end) {
                break@find
            }
        }

        // move UP
        y = y + 1
        i = getNextNum2(board, x, y)
        if (i > end) {
            break@find
        }
        board[x][y] = i
        while (board[x - 1][y] != 0) { // move up, check left
            y = y + 1
            i = getNextNum2(board, x, y) // replace with adjacent sum function for part2
            board[x][y] = i
            if (i > end) {
                break@find
            }
        }

        // move LEFT
        x = x - 1
        i = getNextNum2(board, x, y)
        if (i > end) {
            break@find
        }
        board[x][y] = i
        while (board[x][y - 1] != 0) { // move left, check down
            x = x - 1
            i = getNextNum2(board, x, y) // replace with adjacent sum function for part2
            board[x][y] = i
            if (i > end) {
                break@find
            }
        }


        // move down, check right
        // add in movement when blank
        y = y - 1
        i = getNextNum2(board, x, y)
        if (i > end) {
            break@find
        }
        board[x][y] = i
        while (board[x + 1][y] != 0) {
            y = y - 1
            i = getNextNum2(board, x, y) // replace with adjacent sum function for part2
            board[x][y] = i
            if (i > end) {
                break@find
            }
        }
    }

    println("X " + x)
    println("Y " + y)
    println("i " + i)

    val distance = Math.abs(x - startX) + Math.abs(y - startY)
    println("Dist: " + distance)

//    for (i in 0 until 10) {
//        for (j in 0 until 10) {
//            print(board[j][i])
//            print(" ")
//        }
//        println("\n")
//    }
}

fun getNextNum2(board: Array<IntArray>, x: Int, y: Int): Int {
    var sum = 0
    for (i in -1..1) {
        for (j in -1..1) {
            sum += board[x+i][y+j]
        }
    }

    return sum
}


//fun part3_1() {
//
//    val end = 361527
//    val startX = 302
//    val startY = 302
//    var x = startX
//    var y = startY
//    val board = Array(605, { IntArray(605) })
//
//    board[startX][startY] = 1
//    var i = 1
//    find@ while (i < end) {
//
//        // out of stuff, move _right_ once
//        i = getNextNum(i)
//        x = x + 1
//        board[x][y] = i
//        while (board[x][y + 1] != 0) { // move right, check up
//            i = getNextNum(i) // replace with adjacent sum function for part2
//            x = x + 1
//            board[x][y] = i
//            if (i == end) {
//                break@find
//            }
//        }
//
//        // move UP
//        i = getNextNum(i)
//        y = y + 1
//        board[x][y] = i
//        while (board[x - 1][y] != 0) { // move up, check left
//            i = getNextNum(i) // replace with adjacent sum function for part2
//            y = y + 1
//            board[x][y] = i
//            if (i == end) {
//                break@find
//            }
//        }
//
//        // move LEFT
//        i = getNextNum(i)
//        x = x - 1
//        board[x][y] = i
//        while (board[x][y - 1] != 0) { // move left, check down
//            i = getNextNum(i) // replace with adjacent sum function for part2
//            x = x - 1
//            board[x][y] = i
//            if (i == end) {
//                break@find
//            }
//        }
//
//
//        // move down, check right
//        // add in movement when blank
//        i = getNextNum(i)
//        y = y - 1
//        board[x][y] = i
//        while (board[x + 1][y] != 0) {
//            i = getNextNum(i) // replace with adjacent sum function for part2
//            y = y - 1
//            board[x][y] = i
//            if (i == end) {
//                break@find
//            }
//        }
//    }
//
//    println("X " + x)
//    println("Y " + y)
//    println("i " + i)
//
//    val distance = Math.abs(x - startX) + Math.abs(y - startY)
//    println("Dist: " + distance)
//}
//
//fun getNextNum(i : Int): Int {
//    return i+1
//}



// turn counter idea

//val array = IntArray(end)
//var counter = 1
//var turns = 0
//while (counter < 4) {
//    counter += ctr1
//    turns++
//    if (counter > end) {
//        println(turns)
//        break
//    }
//    counter += ctr2
//    turns++
//    if (counter > end) {
//        println(turns)
//        break
//    }
//    ctr1+=1
//    ctr2+=1
//}
//
//turns = turns - 1 // to back to last position before turn
//println(turns)


//val end = 361527

// Counting Idea
//    var xmax = 0
//    var xmin = 0
//    var ymax = 0
//    var ymin = 0

//var ctr1 = 1
//var ctr2 = 1
//
//val board = Array(605, { IntArray(605) })
//
//var x = 300
//var y = 300
//board[x][y] = 1
//
//
//for (i in 2 until end) {
//
//}

fun part3CtrIdea() {
    // turn counter idea
    val end = 361527
    var ctr1 = 1
    var ctr2 = 1
    var prevCtr = 0
    var counter = 1
    var turns = 0
    while (counter < end) {
        prevCtr = counter
        counter += ctr1
        turns++
        if (counter > end) {
//            println(counter)
            break
        }
        prevCtr = counter
        counter += ctr2
        turns++
        if (counter > end) {
//            println(counter)
            break
        }
        ctr1 += 1
        ctr2 += 1
    }
    turns = turns - 1 // to back to last position before turn
    println("turns " + turns)
    println("prevCounter " + prevCtr)
    println("counter " + counter)
    println("Y diff: " + (end - prevCtr))
}