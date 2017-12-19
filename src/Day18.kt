import java.util.*

fun main(args: Array<String>) {
    part18_2()
}

fun part18_2() { // 4601
    val list = readAsStringList("Day18.txt", "\n")
    var registers0 = HashMap<String, Long>()
    for (i in listOf("a", "p", "i", "b", "f")) {
        registers0.put(i, 0L)
    }

    var registers1 = HashMap<String, Long>()
    for (i in listOf("a", "p", "i", "b", "f")) {
        registers0.put(i, 0L)
    }

    registers0.put("p", 0)
    registers1.put("p", 1)

    var totalOneOutput = 0

    val executeInstruction0 = executeInstruction(0, list, registers0, ArrayDeque())
    var zeroInstr = executeInstruction0.first
    var zeroOutput = executeInstruction0.second
    val executeInstruction1 = executeInstruction(0, list, registers1, ArrayDeque()) // bug here -- should not have 0's
    var oneInstr = executeInstruction1.first
    var zeroInput = executeInstruction1.second
    totalOneOutput += zeroInput.size
    loop@while (true) {

        if (zeroInput.isNotEmpty()) {
            val result0 = executeInstruction(zeroInstr, list, registers0, zeroInput)
            zeroInstr = result0.first
            zeroOutput = result0.second
        }

        if (zeroOutput.isNotEmpty()) {
            val result1 = executeInstruction(oneInstr, list, registers1, zeroOutput)
            oneInstr = result1.first
            zeroInput = result1.second
            totalOneOutput += zeroInput.size // error here, 127, not 132
        }

        if (zeroInput.isEmpty() && zeroOutput.isEmpty()) {
            break@loop
        }

    }

    println("Total one output count:" + totalOneOutput)
}

fun part18_1() { // 4601
    val list = readAsStringList("Day18p.txt", "\n")

    var registers = HashMap<String, Long>()
    registers.put("p", 1)
    var input = ArrayDeque<Long>()
    var output = executeInstruction(0, list, registers, input)
    println(output.second.last())

}

private fun executeInstruction(instrStart : Int, list: ArrayList<String>, registers: HashMap<String, Long>,
                               inputs: Queue<Long>): Pair<Int, ArrayDeque<Long>> {
    var outputs = ArrayDeque<Long>()
    var currentCtr = instrStart
    while (currentCtr >= 0 && currentCtr < list.size) {
        var line = list[currentCtr]
//        println(line)
        val split = line.split(" ")
        val instr = split[0]
        val registerToActOn = split[1]
        var secondArg = ""
        if (split.size > 2) {
            secondArg = split[2]
        }

        if (instr == "snd") {
            val value = if (registers.containsKey(registerToActOn)) {registers.get(registerToActOn)} else registerToActOn.toLong()
            outputs.add(value)
        } else if (instr == "set") {
            if (!registers.containsKey(secondArg)) {
                registers.put(registerToActOn, secondArg.toLong())
            } else {
                val secondVal = registers.get(secondArg)!!
                registers.put(registerToActOn, secondVal)
            }
        } else if (instr == "add") {
            if (!registers.containsKey(secondArg)) {
                val value = getValue(registers, registerToActOn)
                registers.put(registerToActOn, value + secondArg.toLong()) // operator
            } else {
                val secondVal = getValue(registers, secondArg)
                val firstVal = getValue(registers, registerToActOn)
                registers.put(registerToActOn, secondVal + firstVal) // operator
            }
        } else if (instr == "mul") {
            if (!registers.containsKey(secondArg)) {
                val value = getValue(registers, registerToActOn)
                registers.put(registerToActOn, value * secondArg.toLong())
            } else {
                val secondVal = getValue(registers, secondArg)
                val firstVal = getValue(registers, registerToActOn)
                registers.put(registerToActOn, secondVal * firstVal)
            }
        } else if (instr == "mod") {
            if (!registers.containsKey(secondArg)) {
                val value = getValue(registers, registerToActOn)
                registers.put(registerToActOn, value % secondArg.toLong())
            } else {
                val secondVal = getValue(registers, secondArg)
                val firstVal = getValue(registers, registerToActOn)
                registers.put(registerToActOn, firstVal % secondVal)
            }
        } else if (instr == "rcv") { // take the new value
            if (inputs.isEmpty()) {
                return Pair(currentCtr, outputs) // this is right?
            } else {
                val take = inputs.remove()
                registers.put(registerToActOn, take)
            }
        } else if (instr == "jgz") { // bug here: Lesson -- always check inputs -- have a clean way to process inputs so they
            // don't bite you in the butt.  Here, the "jgz 1 3" jumps always
            var value = 0L
            if (!registers.containsKey(registerToActOn)) {
                value = registerToActOn.toLong()
            } else {
                value = registers.get(registerToActOn)!!
            }
            if (value > 0L) {
                currentCtr += (secondArg.toIntOrNull() ?: registers.get(secondArg)!!.toInt())
                continue
            }
        }
        currentCtr++
    }
    return Pair(0, ArrayDeque())
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