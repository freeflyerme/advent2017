fun main(args: Array<String>) {
    part17_2()
}

val SPIN_TIMES = 328 // 3 for test, 328 for real

fun part17_2() {
    val endValue = 50000000

    // I can keep track of 0's position, and each time a new number comes, track of what that index is
    var insertPosition = 0
    var currentNumberAtPos1 = 0
    for (size in 1..endValue) { //size is the current size of the array
        insertPosition = ((insertPosition + SPIN_TIMES) % size) + 1
        if (insertPosition == 1) {
            currentNumberAtPos1 = size
            println(currentNumberAtPos1)
        }
    }

    println(currentNumberAtPos1)

}

fun part17_1() {
    var list = mutableListOf<Int>()
    val endValue = 2017
    for (i in 0..endValue) { // total of 2018 values, but only need to add 2017 times
        list.add(0)
    }


    var insertPosition = 0

    for (size in 1..endValue) { //size is the current size of the array
        // calculate insertion point, insert, move everything else back
        insertPosition = ((insertPosition + SPIN_TIMES) % size) + 1
        // arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        if (insertPosition < size) {
            for (i in size downTo (insertPosition + 1)) {
                list[i] = list[i-1]
            }
        }
        list[insertPosition] = size
    }


    val indexOf2017 = list.indexOf(2017)
    println(list[indexOf2017+1])

    // used for debug with part 2
    println(list)
    val indexOf0 = list.indexOf(0)
    println(indexOf0)
    println(list[indexOf0+1])
}
