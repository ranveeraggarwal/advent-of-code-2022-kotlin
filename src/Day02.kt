// Ugh.
fun main() {
    fun convert(move: String): Int {
        return when (move) {
            "X" -> 0
            "Y" -> 1
            "Z" -> 2
            "A" -> 0
            "B" -> 1
            else -> 2
        }
    }

    fun part1(input: List<String>): Int {
        fun getScore(opp: Int, you: Int): Int {
            return when (opp) {
                0 -> when (you) {
                    1 -> 6
                    2 -> 0
                    else -> 3
                }
                1 -> when (you) {
                    2 -> 6
                    0 -> 0
                    else -> 3
                }
                2 -> when (you) {
                    0 -> 6
                    1 -> 0
                    else -> 3
                }
                else -> 3
            } + you + 1
        }

        return input.fold(0) { sum, line -> sum + getScore(convert(line.split(" ")[0]), convert(line.split(" ")[1])) }
    }

    fun part2(input: List<String>): Int {
        fun getScore(opp: Int, you: Int): Int {
            return when (opp) {
                0 -> when (you) {
                    1 -> 0
                    2 -> 1
                    else -> 2
                }
                1 -> when (you) {
                    1 -> 1
                    2 -> 2
                    else -> 0
                }
                2 -> when (you) {
                    1 -> 2
                    2 -> 0
                    else -> 1
                }
                else -> 3
            } + 1 + you * 3
        }
        return input.fold(0) { sum, line -> sum + getScore(convert(line.split(" ")[0]), convert(line.split(" ")[1])) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
