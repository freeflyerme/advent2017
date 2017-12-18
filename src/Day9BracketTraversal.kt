fun main(args: Array<String>) {
    part9_2()
}

fun part9_2() { // 6569
    val input = readString("Day9.txt")

    // if in <, is in garbage, until stopped by >
    // if !, ignore next character
    // if { ... }, then a group
    // each nested group has an extra point

    var isInGarbage = false
    var isIgnoreNextChar = false
    var groupNest = 0
    var sumScore = 0

    var sumGarbage = 0
    loop@ for (char in input.toCharArray()) {
        // skip if ignore
//        println(char)
        if (isIgnoreNextChar) {
            isIgnoreNextChar = false
        } else if (char == '!') {
            isIgnoreNextChar = true
        } else if (isInGarbage) {
            // check to see if we break out
            if (char == '>') {
                isInGarbage = false

            } else {
                sumGarbage++
            }
        } else if (char == '<') {
            isInGarbage = true
            continue@loop
        } else if (char == '{') {
            groupNest++
            continue@loop
        } else if (char == '}') {
            sumScore += groupNest
            groupNest--
        } else {
            // lastly, do nothing
        }


    }

    println(sumGarbage)

}


fun part9_1() { // 14212
    val input = ClassLoader.getSystemResource("resources/Day9.txt").readText().trim();

    // if in <, is in garbage, until stopped by >
    // if !, ignore next character
    // if { ... }, then a group
    // each nested group has an extra point

    var isInGarbage = false
    var isIgnoreNextChar = false
    var groupNest = 0
    var sumScore = 0

    // {{<a!>},{<a!>},{<a!>},{<ab>}}
    loop@ for (char in input.toCharArray()) {
        // skip if ignore
        if (isIgnoreNextChar) {
            isIgnoreNextChar = false
            continue@loop
        }

        if (char == '!') {
            isIgnoreNextChar = true
            continue@loop
        }

        if (isInGarbage) {
            // check to see if we break out
            if (char == '>') {
                isInGarbage = false

            }
            continue@loop
        }



        if (char == '<') {
            isInGarbage = true
            continue@loop
        }

        if (char == '{') {
            groupNest++
            continue@loop
        }

        if (char == '}') {
            sumScore += groupNest
            groupNest--
            continue@loop
        }

        // lastly, do nothing
    }

    println(sumScore)

}
