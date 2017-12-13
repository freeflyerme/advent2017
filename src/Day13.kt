fun main(args: Array<String>) {
    part13_2()
}

fun part13_2() {
    val firewalls = HashMap<Int, Int>()
    readAsStringList("Day13.txt", "\n").fold(firewalls) {
        accum, line ->
        val split = line.replace(" ", "").split(":")
        accum.put(split[0].toInt(), split[1].toInt())
        accum
    }

//    println(firewalls)

    var delay = 0
    var detection = calculateDetection(firewalls, delay)
    while (detection) {
        delay++
        detection = calculateDetection(firewalls, delay)
//        println("delay:"+delay+",detection:"+detection)
    }

    println(delay)

    // size2: 0, 2, 4
    // size3: 0, 4
    // size4: 0, 6, 12
    // size5: 0, 8, 16
}

private fun calculateDetection(firewalls: HashMap<Int, Int>, delay: Int): Boolean {
    for ((key, value) in firewalls) {
        if ((key + delay) % ((value - 1) * 2) == 0) {
            return true
        }
    }

    return false
}


fun part13_1() {
    val firewalls = HashMap<Int, Int>()
    readAsStringList("Day13.txt", "\n").fold(firewalls) {
        accum, line ->
        val split = line.replace(" ", "").split(":")
        accum.put(split[0].toInt(), split[1].toInt())
        accum
    }

    println(firewalls)

    var severity = 0
    firewalls.forEach { key, value ->

        if (key!=0 && key % ((value - 1) * 2) == 0) {
//            println("key:" + key)
            severity += value * key
        }
    }

    println(severity)

    // pattern
    // size2: 0, 2, 4
    // size 3: 0, 4
    // size 4: 0, 6, 12
    // size5: 0, 8, 16
}

//fun changeablePart() {
//
//}