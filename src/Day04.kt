fun main() {

    fun contains(first: String, second: String): Boolean {
        return ((first.split("-")[0].toInt() <= second.split("-")[0].toInt()) and
                (first.split("-")[1].toInt() >= second.split("-")[1].toInt())) or
                    ((first.split("-")[0].toInt() >= second.split("-")[0].toInt()) and
                    (first.split("-")[1].toInt() <= second.split("-")[1].toInt()))
    }

    fun part1(input: List<String>): Int {
        return input.fold(0) {sum, line -> sum + line.split(",").let { pair -> if (contains(pair[0], pair[1])) 1 else 0}}
    }

    fun overlaps(first: String, second: String): Boolean {
        return ((first.split("-")[0].toInt() <= second.split("-")[0].toInt()) and
                (first.split("-")[1].toInt() >= second.split("-")[0].toInt())) or
                    ((first.split("-")[0].toInt() >= second.split("-")[0].toInt()) and
                    (first.split("-")[0].toInt() <= second.split("-")[1].toInt()))
    }

    fun part2(input: List<String>): Int {
        return input.fold(0) {sum, line -> sum + line.split(",").let { pair -> if (overlaps(pair[0], pair[1])) 1 else 0}}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
