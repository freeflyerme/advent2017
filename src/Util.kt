import java.util.*

fun readAsStringList(fileName : String, separator : String): ArrayList<String> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split(separator)
    return ArrayList(input) // Lesson: inferred return type, no conversion required
}

fun readAsIntList(fileName : String, separator : String): ArrayList<Int> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split(separator).map{ it.toInt() }
    return ArrayList(input)
}

fun readLineByLine(fileName: String): Scanner {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/" + fileName))
    return scan
}

// TODO:
// Practice iterating through collections efficiently