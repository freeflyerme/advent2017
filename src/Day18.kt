fun main(args: Array<String>) {
    part18_1()
}
val LAST_PLAYED = "lastPlayed"

fun part18_1() {
    val list = readAsStringList("Day18.txt", "\n")

    var registers = HashMap<String, Long>()
    registers.put("LAST_PLAYED", 0)
    var currentInstr = 0
//    println(18264348417735L % 2147483647L)
    while (true) {
    // refactor this out into processing
        currentInstr = executeInstruction(list[currentInstr], registers, currentInstr)
        println("  currentInstr:" + currentInstr + " " + registers)
    }

}

private fun executeInstruction(line: String, registers: HashMap<String, Long>, currentInstr: Int): Int {
    println(line)
    val split = line.split(" ")
    val instr = split[0]
    val registerToActOn = split[1]
    var secondArg = ""
    if (split.size > 2) {
        secondArg = split[2]
    }

    if (instr == "snd") {
        val value = getValue(registers, registerToActOn)
        registers.put(LAST_PLAYED, value)
    } else if (instr == "set") {
        if (secondArg.toLongOrNull() != null) {
            registers.put(registerToActOn, secondArg.toLong())
        } else {
            val secondVal = getValue(registers, secondArg)
            registers.put(registerToActOn, secondVal) // operator
        }
    } else if (instr == "add") {
        if (secondArg.toLongOrNull() != null) {
            val value = getValue(registers, registerToActOn)
            registers.put(registerToActOn, value + secondArg.toLong()) // operator
        } else {
            val secondVal = getValue(registers, secondArg)
            val firstVal = getValue(registers, registerToActOn)
            registers.put(registerToActOn, secondVal + firstVal) // operator
        }
    } else if (instr == "mul") {
        if (secondArg.toLongOrNull() != null) {
            val value = getValue(registers, registerToActOn)
            registers.put(registerToActOn, value * secondArg.toLong())
        } else {
            val secondVal = getValue(registers, secondArg)
            val firstVal = getValue(registers, registerToActOn)
            registers.put(registerToActOn, secondVal * firstVal)
        }
    } else if (instr == "mod") {
        if (secondArg.toLongOrNull() != null) {
            val value = getValue(registers, registerToActOn)
            registers.put(registerToActOn, value % secondArg.toLong())
        } else {
            val secondVal = getValue(registers, secondArg)
            val firstVal = getValue(registers, registerToActOn)
            registers.put(registerToActOn, firstVal % secondVal)
        }
    } else if (instr == "rcv") {
        val value = getValue(registers, registerToActOn)
        if (value != 0L) {
            println("Last Played: " + registers.get(LAST_PLAYED))
            throw RuntimeException()
        } else {
            println("registers is 0")
        }
    } else if (instr == "jgz") {
        val value = getValue(registers, registerToActOn)
        if (value > 0L) {
            return currentInstr + secondArg.toInt()
        }
    } else {
        throw IllegalStateException()
    }
    return currentInstr+1
}

private fun getValue(registers: HashMap<String, Long>, registerToActOn: String): Long {
    if (!registers.containsKey(registerToActOn)) {
        registers.put(registerToActOn, 0L)
    }
    return registers.get(registerToActOn) !!
}

//fun changeablePart() {
//
//}