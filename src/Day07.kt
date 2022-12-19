import kotlin.math.min

enum class FileType {
    FILE, DIRECTORY
}

class TreeNode {
    private val fileType: FileType
    private var size: Long = 0
    private val children: HashMap<String, TreeNode> = HashMap()
    private var parent: TreeNode? = null

    private var isDirty = false

    constructor(size: Long) {
        this.fileType = FileType.FILE
        this.size = size
    }

    constructor() {
        this.fileType = FileType.DIRECTORY
    }

    fun addChild(name: String, child: TreeNode) {
        children[name] = child
        child.parent = this
        isDirty = true
    }

    fun getSize(): Long {
        if ((fileType == FileType.DIRECTORY) and isDirty) {
            size = children.values.fold(0) { sum, child -> sum + child.getSize() }
            isDirty = false
        }
        return size
    }

    fun getParent(): TreeNode? {
        return parent
    }

    fun getChild(name: String): TreeNode? {
        return children[name]
    }

    // Part 1
    fun sumOfSmallDirectories(): Long {
        if (fileType == FileType.FILE) return 0L
        return (if (size > 100000) 0L else size) + children.values.fold(0L) { sum, child -> sum + child.sumOfSmallDirectories() }
    }

    // Part 2
    fun findSmallDirectorySizeToDelete(deletionNeeded: Long): Long {
        if (fileType == FileType.FILE) return Long.MAX_VALUE
        return min(
            children.values.minOf { it.findSmallDirectorySizeToDelete(deletionNeeded) },
            returnLargeIfOverLimit(deletionNeeded, getSize())
        )
    }
}

fun returnLargeIfOverLimit(deletionNeeded: Long, currSize: Long): Long {
    return if (currSize > deletionNeeded) currSize else Long.MAX_VALUE
}

fun buildDirectory(input: List<String>): TreeNode {
    val root = TreeNode()
    var cursor = root
    input.forEach { line ->
        run {
            val command = line.split(' ')
            if (command[0] == "$") {
                if (command[1] == "cd") {
                    cursor = if (command[2] == "..") cursor.getParent()!! else cursor.getChild(command[2])!!
                }
            } else {
                if (command[0] == "dir") {
                    cursor.addChild(command[1], TreeNode())
                } else {
                    cursor.addChild(command[1], TreeNode(command[0].toLong()))
                }
            }
        }
    }
    return root
}

fun main() {
    fun part1(input: List<String>): Long {
        val root = buildDirectory(input.drop(1))
        root.getSize()
        return root.sumOfSmallDirectories()
    }

    fun part2(input: List<String>): Long {
        val root = buildDirectory(input.drop(1))
        return root.findSmallDirectorySizeToDelete(30000000 - (70000000 - root.getSize()))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
