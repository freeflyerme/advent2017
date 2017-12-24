import java.util.*

fun main(args: Array<String>) {
    part23_2()
}

fun part23_2() { // result in "h", starting at instruction 1
    // 9401 is too high
    var ct = 0
    for (i in 106500..123500 step 17) {
        if(!isPrime(i)) {
            ct++
        }
    }
    println(ct)
//    val list = readAsStringList("Day23.txt", "\n")
//
//    var registers = HashMap<String, Long>()
//    registers.put("a", 1)  // Lesson: read the instructions carefully -- only A as a register is changed.  The rest remains 0
//    registers.put("b", 0)
//    registers.put("c", 0)
//    registers.put("d", 0)
//    registers.put("e", 0)
//    registers.put("f", 0)
//    registers.put("g", 0)
//    registers.put("h", 0)
//
//    var input = ArrayDeque<Long>()
//    var output = executeInstruction(0, list, registers, 1000)
//    println(output)
}

private fun isPrime(input: Int): Boolean {
    for (i in 2..Math.sqrt(input.toDouble()).toInt()) {
        if (input % i == 0) {
            return false
        }
    }
    return true
}

fun part23_1() { // 3969 -- number of times h executes
    val list = readAsStringList("Day23.txt", "\n")

    var registers = HashMap<String, Long>()
    registers.put("a", 0)
    registers.put("b", 0)
    registers.put("c", 0)
    registers.put("d", 0)
    registers.put("e", 0)
    registers.put("f", 0)
    registers.put("g", 0)
    registers.put("h", 0)

    var output = executeInstruction(0, list, registers, 200000)
    println(output)

}

private fun executeInstruction(instrStart : Int, list: ArrayList<String>, registers: HashMap<String, Long>, maxIter : Int = 1000): Long {
    var currentCtr = instrStart
    var mulCnt = 0

    var iter = 0

    while (currentCtr >= 0 && currentCtr < list.size) {
        iter++
        if (maxIter == iter) {
            break
        }

        var line = list[currentCtr]
        println(registers)
        println("$currentCtr: $line")

        if (registers.get("h") == 2L) {
            println("$registers, $iter")
        }

        val split = line.split(" ")
        val instr = split[0]
        val firstArg = split[1]
        var secondArg = ""
        if (split.size > 2) {
            secondArg = split[2]
        }

        if (instr == "set") {
            if (!registers.containsKey(secondArg)) {
                // Lesson: this implementation is unoptimized -- I needed a more concise and
                // less error  prone  way of dealing with operators.  The % and - operators
                // both errored because the order of operations were off.
                registers.put(firstArg, secondArg.toLong())
            } else {
                val secondVal = registers.get(secondArg)!!
                registers.put(firstArg, secondVal)
            }
        } else if (instr == "sub") {
            if (!registers.containsKey(secondArg)) {
                val value = getValue(registers, firstArg)
                registers.put(firstArg, value - secondArg.toLong()) // operator
            } else {
                val secondVal = getValue(registers, secondArg)
                val firstVal = getValue(registers, firstArg)
                registers.put(firstArg, firstVal - secondVal) // operator
            }
        } else if (instr == "mul") {
            if (!registers.containsKey(secondArg)) {
                val value = getValue(registers, firstArg)
                registers.put(firstArg, value * secondArg.toLong())
            } else {
                val secondVal = getValue(registers, secondArg)
                val firstVal = getValue(registers, firstArg)
                registers.put(firstArg, secondVal * firstVal)
            }
            mulCnt ++
        } else if (instr == "jnz") { // bug here: Lesson -- always check inputs -- have a clean way to process inputs so they
            // don't bite you in the butt.  Here, the "jgz 1 3" jumps always
            var value : Long
            if (!registers.containsKey(firstArg)) {
                value = firstArg.toLong()
            } else {
                value = registers.get(firstArg)!!
            }
            if (value != 0L) {
                currentCtr += (secondArg.toIntOrNull() ?: registers.get(secondArg)!!.toInt())
                continue
            }
        }
        currentCtr++
    }
    return getValue(registers, "h")
}

private fun getValue(registers: HashMap<String, Long>, registerToActOn: String): Long {
    if (!registers.containsKey(registerToActOn)) {
        throw RuntimeException()
    }
    return registers.get(registerToActOn) !!
}