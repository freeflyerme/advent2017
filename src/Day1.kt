// https://adventofcode.com/2017/day/1
fun main(args : Array<String>) {
    part1()
    part1Redux()
    part2()
    part2Redux()
}

fun part2Redux() { // no redundant return type
    val input = ClassLoader.getSystemResource("resources/Day1_1.txt").readText().trim();
    val sum = calculateSum(input, ::getNextIndexPart2) // Lesson: pass a method in as a lambda
    println(sum)
}

fun getNextIndexPart2(input: Int, size: Int): Int {
    return ((size / 2) + input ) % size
}

fun part1Redux() { // parameterized nextIndexPart1 into a function
    val input = ClassLoader.getSystemResource("resources/Day1_1.txt").readText().trim();

    var sum = calculateSum(input, ::getNextIndexPart1)
    println(sum)

}

// optimized shared components for both parts
private fun calculateSum(input: String, nextIntFun: (Int, Int) -> Int): Int {
    var sum = 0;
    for (i in 0 until input.length) {
        val nextIndex = nextIntFun(i, input.length)
        if (input[i].equals(input[nextIndex])) {
            sum += Character.getNumericValue(input[i])
        }
    }
    return sum
}

fun getNextIndexPart1(i: Int, length: Int): Int {
    if (i == length - 1) {
        return 0
    }
    return i+1;
}


// Initial solution, part2
fun part2() { // no redundant return type
    val input = ClassLoader.getSystemResource("resources/Day1_1.txt").readText().trim();
    var sum = 0;

    for (i in 0 until input.length / 2) { // Lesson: Exclusive of the last element
        val nextIndex = getNextIndexPart2(i, input.length)
        if (input[i].equals(input[nextIndex])) {
            sum += toInt(input[i])
        }
    }

    sum *= 2 // the end result always doubles -- without optimization, can share code with Part1 Redux
    println(sum)
}

// Initial solution, part1
fun part1(): Unit {
    val input = ClassLoader.getSystemResource("resources/Day1_1.txt").readText().trim();

    val iterator = input.iterator();

    var previous = iterator.next();
    var sum: Int = 0

    while (iterator.hasNext()) {
        val current = iterator.next()
        if (current.equals(previous)) {
            sum += toInt(current)
        }
        previous = current
    }

    if (input.last().equals(input.first())) {
        sum += toInt(input.last())
    }

    println(sum)

}

fun toInt(input: Char): Int {
    return Integer.parseInt(input.toString())
}