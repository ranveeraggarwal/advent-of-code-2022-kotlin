fun main() {
    fun printFirstUnique(seq: String, windowSize: Int) {
        val windows = seq.windowed(windowSize, 1)
        for (i in windows.indices) {
            if (windows[i].toSet().size == windowSize) {
                println(i + windowSize)
                return
            }
        }
    }

    fun part1(input: List<String>): Int {
        input.forEach { printFirstUnique(it, 4) }
        return 0
    }

    fun part2(input: List<String>): Int {
        input.forEach { printFirstUnique(it, 14) }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    part1(testInput)
    part2(testInput)

    val input = readInput("Day06")
    part1(input)
    part2(input)
}
