import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
//    println("Hello world!")
    part4_1()
    part4_2()
}

// Stream.distinct

fun part4_1() {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/Day4_1.txt"))
    var valid = 0
    while (scan.hasNextLine()) {
        val line = scan.nextLine()
        val split = line.split(" ")
        if (isValid(split)) {
            valid++
        }

    }
    println("valid=" + valid)
}

fun part4_2() {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/Day4_1.txt"))
    var valid = 0
    while (scan.hasNextLine()) {
        val line = scan.nextLine()
        val split = line.split(" ")
        if (isValid2(split)) {
            valid++
        }

    }
    println("valid=" + valid)
}

fun isValid2(split: List<String>): Boolean {
    var set = HashSet<HashMap<Char, Int>>()
    for (word in split) {
        val wordSoup = toWordSoup(word)
        if (set.contains(wordSoup)) {
            return false
        }
        set.add(wordSoup)
    }
    return true
}

fun toWordSoup(input : String): HashMap<Char, Int> {
    var result = HashMap<Char, Int>()
    for (c in input) {
        // Lesson: optimization using the optional -- default to a sensible value and always override
        var occur = result.get(c) ?: 0
        occur++
        result.put(c, occur)

    }
    return result
}

fun isValid(split: List<String>): Boolean {
    // alternative: check distinct count vs count
    // split.distinct().size == split.size
    var set = HashSet<String>()
    for (word in split) {
        if (set.contains(word)) {
            return false
        }
        set.add(word)
    }
    return true
}