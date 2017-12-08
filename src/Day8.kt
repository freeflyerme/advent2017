fun main(args: Array<String>) {
    part8_1()
}

fun part8_1() {
    // operations: inc / dec
    // registers -- could be any -- use a hashmap, with values
    // comparisons, ==, !=, >, <, <=, >=
    val scanner = readLineByLine("Day8.txt")
    // read line, parse into components
    // execute condition, update register

    val registers = HashMap<String, Int>()

    // b inc 5 if a > 1
    var maxVal = 0
    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        val split = line.split(" ")
        val regToMod = split[0]
        val action = split[1]
        val actionNum = split[2].toInt()
        val regToCheck = split[4]
        val comparison = split[5]
        val comparisonVal = split[6].toInt()

        // initialize register, if not exist
        if (!registers.containsKey(regToMod)) {
            registers.put(regToMod, 0)
        }

        if (!registers.containsKey(regToCheck)) {
            registers.put(regToCheck,0)
        }

        val regToModVal = registers.get(regToMod) !!
        val regToCheckValue = registers.get(regToCheck) !!

        if (comparisonResults(comparison, regToCheckValue, comparisonVal)) {
            if (action == "inc") {
                registers.put(regToMod, regToModVal + actionNum)
            } else {
                registers.put(regToMod, regToModVal - actionNum)
            }
            if (maxVal < registers.get(regToMod) !!) {
                maxVal = registers.get(regToMod) !!
            }
        }
    }

    println(registers.values.max())
    println(maxVal) // part2, keep track of highest number

}

fun comparisonResults(comparison: String, regToCheck: Int, comparisonVal: Int): Boolean {
    when(comparison) {
        "==" -> return regToCheck == comparisonVal
        "!=" -> return regToCheck != comparisonVal
        ">=" -> return regToCheck >= comparisonVal
        "<=" -> return regToCheck <= comparisonVal
        "<" -> return regToCheck < comparisonVal
        ">" -> return regToCheck > comparisonVal
    }
    return true
}



