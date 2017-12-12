package code

import readString
import kotlin.math.abs

fun main(args: Array<String>) {  // stu's solution, doesn't account for all varibles, but worked for the input due one dimension
// being always greater than the other.
    val readString = readString("Day11.txt")
    println(day11problem1(readString))
    println(day11problem2(readString))

}

fun day11problem1(inputString: String): Any? {

    var normalizedLine = inputString.replace('\t', ' ')

    var directions = normalizedLine.split(",".toRegex())

    var vertCount = 0
    var horCount = 0

    for (direction in directions) {
        for(i in 0..direction.length - 1) {
            if (direction.get(i).equals('n')) {
                vertCount += 1
            }
            if (direction.get(i).equals('s')) {
                vertCount -= 1
            }
            if (direction.get(i).equals('e')) {
                horCount += 1
            }
            if (direction.get(i).equals('w')) {
                horCount -= 1
            }
        }
    }
    if(abs(vertCount) > abs(horCount)){
        return (abs(vertCount) - abs(horCount)) /2 + abs(horCount)
    }

    return abs(horCount)
}

fun day11problem2(inputString: String): Any? {

    var normalizedLine = inputString.replace('\t', ' ')

    var directions = normalizedLine.split(",".toRegex())

    var vertCount = 0
    var horCount = 0
    var max = 0

    for (direction in directions) {
        for(i in 0..direction.length - 1) {
            if (direction.get(i).equals('n')) {
                vertCount += 1
            }
            if (direction.get(i).equals('s')) {
                vertCount -= 1
            }
            if (direction.get(i).equals('e')) {
                horCount += 1
            }
            if (direction.get(i).equals('w')) {
                horCount -= 1
            }
        }
        if(abs(vertCount) > max){
            if(max < (abs(vertCount) - abs(horCount)) /2 + abs(horCount)) {
                max = (abs(vertCount) - abs(horCount)) /2 + abs(horCount)
            }
        }
        if(abs(horCount) > max){
            max = abs(horCount)
        }
    }
    if(abs(vertCount) > abs(horCount)){
        return abs(vertCount)
    }

    return max
}