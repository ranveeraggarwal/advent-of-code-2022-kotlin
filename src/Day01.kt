fun main() {
    fun splitOnEmpty(input: List<String>): ArrayList<Int> {
        val allCalories = ArrayList<Int>()
        var current = 0
        input.forEach {
            if (it.isEmpty()) {
                allCalories.add(current)
                current = 0
            } else {
                current += Integer.parseInt(it)
            }
        }
        if (current > 0) allCalories.add(current)
        allCalories.sortDescending()
        return allCalories
    }

    fun part1(input: List<String>): Int {
        return splitOnEmpty(input).first()
    }

    fun part2(input: List<String>): Int {
        return splitOnEmpty(input).take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
