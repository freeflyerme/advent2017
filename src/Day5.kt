// Lesson: Kotlin Streams -- simplified like Groovy streams
// https://stackoverflow.com/questions/34642254/what-java-8-stream-collect-equivalents-are-available-in-the-standard-kotlin-libr

// Lesson: Understand the initial direction, there's usually no weird one time initialization action
//         Careful with constraints -- small or large
fun main(args: Array<String>) {
    part5_1()
    part5_2()
}

fun part5_1() {
    var numbers = readAsIntList("Day5.txt")
    val size = numbers.size
    var current = 0
    var next = current

    var jumpCtr = 0
    // increment (initial one time), calculate next position, increment current
    while (current >= 0 && current < size) { // 381680

        next = numbers[current] + current
        numbers[current]++
        current = next
        jumpCtr++
    }

    println("jumps:"+ jumpCtr)
}


fun part5_2() {
    var numbers = readAsIntList("Day5.txt")

    val size = numbers.size
    var current = 0
    var next = current

    var jumpCtr = 0
    // increment (initial one time), calculate next position, increment current
    while (current >= 0 && current < size) { // 29717847

        next = numbers[current] + current
        if (numbers[current] >= 3) {
            numbers[current] = numbers[current] - 1
        } else {
            numbers[current] = numbers[current] + 1
        }
        current = next
        jumpCtr++
    }

    println("jumps:"+ jumpCtr)
}
