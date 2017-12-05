import java.util.*

// Lesson: Kotlin Streams -- simplified like Groovy streams
// https://stackoverflow.com/questions/34642254/what-java-8-stream-collect-equivalents-are-available-in-the-standard-kotlin-libr

// Lesson: Understand the initial direction, there's usually no weird one time initialization action
//         Careful with constraints -- small or large
fun main(args: Array<String>) {
    part5_1()
    part5_2()
}

fun part5_1() {
    val input = ClassLoader.getSystemResource("resources/Day5.txt").readText().trim();
    var numbersList: List<Int> = input.split("\n").map{ Integer.parseInt(it) }
    var numbers = ArrayList(numbersList)

    val size = numbers.size
    var current = 0
    var next = current

    var jumpCtr = 0
    // increment (initial one time), calculate next position, increment current
    while (current >= 0 && current < size) { // 381680

        next = numbers[current] + current
        numbers[current] = numbers[current] + 1
        current = next
        jumpCtr++
    }

    println("jumps:"+ jumpCtr)
}


fun part5_2() {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/Day5.txt"))
    var numbers = ArrayList<Int>()
    while (scan.hasNextLine()) {
        numbers.add(Integer.parseInt(scan.nextLine()))
    }

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
