fun main(args: Array<String>) {
    part24_2()
}

data class Link constructor (val ends : ArrayList<Int>)


fun part24_2() { // not 1964
    // Need longest bridge

    // What structure to use?  A list?  Sure
    val links = readAsStringList("Day24.txt", "\n").map { line ->
        var ends = ArrayList<Int>()
        val split = line.split("/")
        ends.add(split[0].toInt())
        ends.add(split[1].toInt())
        Link(ends)
    }

    val validStarts = links.filter { it.ends.contains(0) }

    val result = validStarts.map { startLink ->
        var mutableLinks = ArrayList<Link>()
        mutableLinks.addAll(links)
        val max = findMaxStrength2(startLink, mutableLinks, 0, startLink.ends.max()!!, 0)
        max
    }
    val longestBridge: Pair<Int, Int> = result.maxBy { it.second }!!
    val maxStrengths = result.filter { it.second == longestBridge.second }
    println(maxStrengths.maxBy { it.first }!!)

}

private fun findMaxStrength2(startLink: Link, mutableLinks: ArrayList<Link>, currentMax: Int, endToMatch: Int, length: Int):
        Pair<Int, Int> {

    var newMax = currentMax + startLink.ends.sum()
    var links = ArrayList<Link>() // Lesson: mutable list modified by below.  Need a clone
    links.addAll(mutableLinks)
    links.remove(startLink)

    val matches = links.filter { it.ends.contains(endToMatch) }
    if (matches.isEmpty()) {
        return Pair(newMax, length + 1)
    } else {
        val resultPairs = matches.map { match ->
            findMaxStrength2(match,
                    links,
                    newMax,
                    if (match.ends.first() == endToMatch) match.ends.last() else match.ends.first(), length + 1)
        }
        val longestBridge: Pair<Int, Int> = resultPairs.maxBy { it.second }!!
        val maxStrengths = resultPairs.filter { it.second == longestBridge.second }
        return maxStrengths.maxBy { it.first }!!
    }
}

fun part24_1() { // not 1554
    // Algorithm:
    /**
     * Either take it, or without it.
     * with a starting point.
     * Put into a list, then find max
     */

    // What structure to use?  A list?  Sure
    val links = readAsStringList("Day24.txt", "\n").map { line ->
        var ends = ArrayList<Int>()
        val split = line.split("/")
        ends.add(split[0].toInt())
        ends.add(split[1].toInt())
        Link(ends)
    }

    val validStarts = links.filter { it.ends.contains(0) }

    val result = validStarts.map { startLink ->
        var mutableLinks = ArrayList<Link>()
        mutableLinks.addAll(links)
        val max = findMaxStrength(startLink, mutableLinks, 0, startLink.ends.max()!!)
        max
    }.max()
    println(result)
}

private fun findMaxStrength(startLink: Link, mutableLinks: ArrayList<Link>, currentMax: Int, endToMatch: Int): Int {
    // base cases:
    // 1. find other matches
    //   no match -- done, return newMax
    //   found a matches, recursive on all of them
    var newMax = currentMax + startLink.ends.sum()
    var links = ArrayList<Link>() // Lesson: mutable list modified by below.  Need a clone
    links.addAll(mutableLinks)
    links.remove(startLink)

    val matches = links.filter { it.ends.contains(endToMatch) }
    if (matches.isEmpty()) {
        return newMax
    } else {
        return matches.map { match ->
            findMaxStrength(match,
                    links,
                    newMax,
                    if (match.ends.first() == endToMatch) match.ends.last() else match.ends.first())
        }.max()!!
    }

}
