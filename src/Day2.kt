import java.util.*

class Day2 {
    fun part22() {
        // search the area and get the only two numbers that divided each other
        val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/Day2_1.txt"))
        var checkSums = ArrayList<Int>()
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            val result = line.split("\t")
            val resultInt = result.map { e -> Integer.parseInt(e) }
            val check = getDivisibleResultChecksum(resultInt)
            checkSums.add(check)
        }

        println(checkSums.sum())
    }

    fun getDivisibleResultChecksum(result: List<Int>): Int { // lesson for loops
        result.forEach { e ->
            result.forEach { other ->
                if (e != other && e % other == 0) {
                    return e / other
                }
            }
        }
        return 0;
    }

    fun part21() {
//    val input = ClassLoader.getSystemResource("resources/Day2_1.txt").readText().trim()
        val scan = Scanner(ClassLoader.getSystemResourceAsStream("resources/Day2_1.txt"))
        var checkSums = ArrayList<Int>()
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            val result = line.split("\t")
            val resultInt = result.map { e -> Integer.parseInt(e) }
            val check = getChecksum(resultInt)
            checkSums.add(check)
        }

        println(checkSums.sum())

    }

    private fun getChecksum(result: List<Int>): Int {
        val largest = result.max() ?: 0
        val smallest: Int = result.min() ?: 0
        return largest - smallest
    }
}

// Deal with namespaces -- this is an experimentation
// The proper way to deal with namespaces is to have different packages.  Package level functions are fine -- e.g. not putting
// functions inside classes like Java.
// There is no default static methods in Java
fun main(args: Array<String>) {
    val day2 = Day2()
    day2.part21()
    day2.part22()
}

