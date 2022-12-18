fun main() {
    fun getPriority(c: Char): Int {
        return if (c.isUpperCase()) c.code - 'A'.code + 27 else c.code - 'a'.code + 1
    }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach {
            val map = HashSet<Char>()
            it.substring(0, it.length / 2).forEach { c -> map.add(c) }
            result += it.substring(it.length / 2).filter { c -> map.contains(c) }.toSet()
                .fold(0) { sum, c -> sum + getPriority(c) }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).fold(0) { sum, group ->
            sum + getPriority(
                group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet()).first()
            )
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))

}
