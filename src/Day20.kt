import kotlin.math.abs

fun main(args: Array<String>) {
    part20_2()
}

fun part20_2() { // sort by min acceleration, then min velocity, and finally min position
    val input = readAsStringList("Day20.txt", "\n")

    var points = ArrayList<Pair<Int, Triple<Int,Int,Int>>>()
    var vels = ArrayList<Pair<Int, Triple<Int,Int,Int>>>()
    var accels = ArrayList<Pair<Int, Triple<Int,Int,Int>>>()

    var ctr = 0
    for (i in input) {
        val split = i.replace("<", "").replace(">", "").replace(" ", "").replace("=", ",").split(",")
        points.add(Pair(ctr, Triple(split[1].toInt(), split[2].toInt(), split[3].toInt())))
        vels.add(Pair(ctr, Triple(split[5].toInt(), split[6].toInt(), split[7].toInt())))
        accels.add(Pair(ctr, Triple(split[9].toInt(), split[10].toInt(), split[11].toInt())))
        ctr++
    }

    for (i in 1..5000) {
        val newVels = vels.zip(accels).map { it ->  // this is some deep stuff right here
            Pair(it.first.first, Triple(it.first.second.first + it.second.second.first,
                    it.first.second.second + it.second.second.second, it.first.second.third + it.second.second.third))
        }
        vels.clear()
        vels.addAll(newVels)

        val newPoints = points.zip(vels).map { it ->
            Pair(it.first.first, Triple(it.first.second.first + it.second.second.first,
                    it.first.second.second + it.second.second.second, it.first.second.third + it.second.second.third))
        }
        points.clear()
        points.addAll(newPoints)

        var toRemove = ArrayList<Pair<Int, Triple<Int,Int,Int>>>()
        points.forEach { point ->
            val collisionPoints = points.filter { it.second == point.second && it.first != point.first }
            if (collisionPoints.isNotEmpty()) {
                toRemove.add(point)
                toRemove.addAll(collisionPoints)
            }
        }
        points.removeAll(toRemove)

        if (toRemove.isEmpty()) {
            continue;
        }

        val aIt = accels.iterator()
        while (aIt.hasNext()) {
            if (toRemove.map{ it.first }.contains(aIt.next().first)) {
                aIt.remove()
            }
        }

        val vIt = vels.iterator()
        while (vIt.hasNext()) {
            if (toRemove.map{ it.first }.contains(vIt.next().first)) {
                vIt.remove()
            }
        }

    }

    println(points.size)
    // basically, get the lines, recalculate velocity , update position,
    // trac position overtime, and see who's closest

}

fun part20_1() { // sort by min acceleration, then min velocity, and finally min position, answer: 344
    val input = readAsStringList("Day20.txt", "\n")

    var points = ArrayList<Triple<Int,Int,Int>>()
    var vels = ArrayList<Triple<Int,Int,Int>>()
    var accels = ArrayList<Triple<Int,Int,Int>>()

    for (i in input) {
        val split = i.replace("<", "").replace(">", "").replace(" ", "").replace("=", ",").split(",")
        points.add(Triple(split[1].toInt(), split[2].toInt(), split[3].toInt()))
        vels.add(Triple(split[5].toInt(), split[6].toInt(), split[7].toInt()))
        accels.add(Triple(split[9].toInt(), split[10].toInt(), split[11].toInt()))
    }

    // want the smallest acceleration, velocity, then position
//    val accelerationDist = accels.map { manhanttanDist(it) }
//    val velDist = vels.map { manhanttanDist(it) }
//    val posDist = points.map { manhanttanDist(it) }
//    val sortedByA = accelerationDist.withIndex().sortedBy { it.value }.filter { it.value == accelerationDist.min() }.map{it.index}
//    val sortedByAV = velDist.withIndex().filter{ sortedByA.contains(it.index) }
//    println(sortedByAV)
//    println(sortedByAV.minBy { it.value })


    // analytical solution:
    var closest = 0
    for (i in 1..1000) {
        val newVels = vels.zip(accels).map { it ->
            Triple(it.first.first + it.second.first, it.first.second + it
                    .second
                    .second, it.first.third + it.second.third)
        }
        vels.clear()
        vels.addAll(newVels)

        val newPoints = points.zip(vels).map { it ->
            Triple(it.first.first + it.second.first, it.first.second + it
                    .second
                    .second, it.first.third + it.second.third)
        }
        points.clear()
        points.addAll(newPoints)

        val distances = points.map { triple -> manhanttanDist(triple) }
        val closestPoint = distances.indexOf(distances.min())
        println("i: $i, closestPoint: $closestPoint, dist: ${distances.min()}")
        closest = closestPoint
    }

    println(closest)
}

fun manhanttanDist(input: Triple<Int, Int, Int>): Int {
    return abs(input.first) + abs(input.second) + abs(input.third)
}


