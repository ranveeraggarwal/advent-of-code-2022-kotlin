fun main() {
    fun part1(input: List<String>): String {
        val numStacks = input[0].length / 4 + 1
        val stacks = Array<ArrayDeque<Char>>(numStacks) { ArrayDeque() }
        var cursor = 0

        // process initial state
        while (true) {
            if (input[cursor][1] == '1') break
            (input[cursor] + " ").chunked(4).forEachIndexed { index, entry ->
                if (entry[1] != ' ') {
                    stacks[index].addLast(entry[1])
                }
            }
            cursor++
        }
        cursor += 2

        // process instructions
        for (i in cursor until input.size) {
            val parsedInstructions =
                input[cursor].filter { it.isDigit() || it == ' ' }.split(' ').filter { it.isNotEmpty() }
                    .map { it.toInt() }
            for (j in 0 until parsedInstructions[0]) {
                stacks[parsedInstructions[2] - 1].addFirst(stacks[parsedInstructions[1] - 1].first())
                stacks[parsedInstructions[1] - 1].removeFirst()
            }
            cursor++
        }

        return stacks.fold("") { sum, entry -> sum + entry.first() }
    }

    fun part2(input: List<String>): String {
        val numStacks = input[0].length / 4 + 1
        val stacks = Array<ArrayDeque<Char>>(numStacks) { ArrayDeque() }
        var cursor = 0

        // process initial state
        while (true) {
            if (input[cursor][1] == '1') break
            (input[cursor] + " ").chunked(4).forEachIndexed { index, entry ->
                if (entry[1] != ' ') {
                    stacks[index].addLast(entry[1])
                }
            }
            cursor++
        }
        cursor += 2

        // process instructions
        for (i in cursor until input.size) {
            val parsedInstructions =
                input[cursor].filter { it.isDigit() || it == ' ' }.split(' ').filter { it.isNotEmpty() }
                    .map { it.toInt() }
            val stack = ArrayDeque<Char>()
            for (j in 0 until parsedInstructions[0]) {
                stack.addFirst(stacks[parsedInstructions[1] - 1].first())
                stacks[parsedInstructions[1] - 1].removeFirst()
            }
            for (j in 0 until parsedInstructions[0]) {
                stacks[parsedInstructions[2] - 1].addFirst(stack.first())
                stack.removeFirst()
            }
            cursor++
        }

        return stacks.fold("") { sum, entry -> sum + entry.first() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
