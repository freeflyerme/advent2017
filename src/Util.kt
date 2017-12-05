import java.util.*

fun readAsStringList(fileName : String): ArrayList<String> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split("\n")
    return ArrayList(input) // Lesson: inferred return type, no conversion required
}

fun readAsIntList(fileName : String): ArrayList<Int> {
    val input = ClassLoader.getSystemResource("resources/" + fileName).readText().trim().split("\n").map{ it.toInt() }
    return ArrayList(input)
}

fun readLineByLine(fileName: String): Scanner {
    val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/" + fileName))
    return scan
}