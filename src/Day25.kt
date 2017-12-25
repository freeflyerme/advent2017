fun main(args: Array<String>) {
    part25_1()
}

fun part25_1() {
    var infiniteTape = Array(10000, {0})
    val startPos = infiniteTape.size / 2
    val startState = States.A

    val endSteps = 12586542 // 6 is practice

    var stepCtr = 0
    var pos = startPos
    var state = startState

    while(stepCtr < endSteps) {
        val executeStep = executeStep(infiniteTape, pos, state)
        pos = executeStep.first
        state = executeStep.second
        stepCtr++

//        infiniteTape.forEach {
//            print("$it ")
//        }
//        println("pos: $pos, stepCtr: $stepCtr")

    }

    val checksum = infiniteTape.sum()


    println("\n"+checksum)

}

fun executeStep(infiniteTape: Array<Int>, pos: Int, startState: States): Pair<Int, States> {
    var newPos = pos
    var nextState = startState
    val currentVal = infiniteTape[pos]

    when (startState) {
        States.A ->
            //    In state A:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the right.
//    - Continue with state B.
//    If the current value is 1:
//    - Write the value 0.
//    - Move one slot to the left.
//    - Continue with state B.
            if (currentVal == 0) {
                infiniteTape[pos] = 1
                newPos = pos+1
                nextState = States.B
            } else {
                infiniteTape[pos] = 0
                newPos = pos-1
                nextState = States.B
            }
    //    In state B:
//    If the current value is 0:
//    - Write the value 0.
//    - Move one slot to the right.
//    - Continue with state C.
//    If the current value is 1:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state B.
        States.B ->
            if (currentVal == 0) {
                infiniteTape[pos] = 0
                newPos = pos+1
                nextState = States.C
            } else {
                infiniteTape[pos] = 1
                newPos = pos-1
                nextState = States.B
            }
    //    In state C:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the right.
//    - Continue with state D.
//    If the current value is 1:
//    - Write the value 0.
//    - Move one slot to the left.
//    - Continue with state A.
        States.C ->
            if (currentVal == 0) {
                infiniteTape[pos] = 1
                newPos = pos+1
                nextState = States.D
            } else {
                infiniteTape[pos] = 0
                newPos = pos-1
                nextState = States.A
            }
    //    In state D:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state E.
//    If the current value is 1:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state F.
        States.D ->
            if (currentVal == 0) {
                infiniteTape[pos] = 1
                newPos = pos-1
                nextState = States.E
            } else {
                infiniteTape[pos] = 1
                newPos = pos-1
                nextState = States.F
            }
    //    In state E:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state A.
//    If the current value is 1:
//    - Write the value 0.
//    - Move one slot to the left.
//    - Continue with state D.
        States.E ->
            if (currentVal == 0) {
                infiniteTape[pos] = 1
                newPos = pos-1
                nextState = States.A
            } else {
                infiniteTape[pos] = 0
                newPos = pos-1
                nextState = States.D
            }
    //    In state F:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the right.
//    - Continue with state A.
//    If the current value is 1:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state E.
        States.F ->
            if (currentVal == 0) {
                infiniteTape[pos] = 1
                newPos = pos+1
                nextState = States.A
            } else {
                infiniteTape[pos] = 1
                newPos = pos-1
                nextState = States.E
            }
    }


    return Pair(newPos, nextState)
}

fun executeStepPractice(infiniteTape: Array<Int>, pos: Int, startState: States): Pair<Int, States> {
    var newPos = pos
    var nextState = startState
    val currentVal = infiniteTape[pos]
    // practice inputs
//    In state A:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the right.
//    - Continue with state B.
//    If the current value is 1:
//    - Write the value 0.
//    - Move one slot to the left.
//    - Continue with state B.
//
//    In state B:
//    If the current value is 0:
//    - Write the value 1.
//    - Move one slot to the left.
//    - Continue with state A.
//    If the current value is 1:
//    - Write the value 1.
//    - Move one slot to the right.
//    - Continue with state A.
    when (startState) {
        States.A ->
                if (currentVal == 0) {
                    infiniteTape[pos] = 1
                    newPos = pos+1
                    nextState = States.B
                } else {
                    infiniteTape[pos] = 0
                    newPos = pos-1
                    nextState = States.B
                }
        States.B ->
                if (currentVal == 0) {
                    infiniteTape[pos] = 1
                    newPos = pos-1
                    nextState = States.A
                } else {
                    infiniteTape[pos] = 1
                    newPos = pos+1
                    nextState = States.B
                }
    }


    return Pair(newPos, nextState)
}

 enum class States {
    A, B, C, D, E, F
}