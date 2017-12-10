fun main(args: Array<String>) {
    val map = HashMap<String, String>()
    map.put("a", "1")
    map.put("b", "2")

    for ( (key, value) in map) {// Lesson: Destructuring
        println("Key:"+key+", Value:"+value)
    }

    // Lesson: Collections and read only views: https://kotlinlang.org/docs/reference/collections.html
    val original = mutableListOf(1,2,3)
    val readOnly: List<Int> = original  // the List<Int> creates read only view, internal
    // readOnly.add(1) // doesn't compile, the add doesn't work
    println(readOnly)
}