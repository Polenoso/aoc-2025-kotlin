/**
 * --- Day 4: Printing Department ---
 * You ride the escalator down to the printing department. They're clearly getting ready for Christmas; they have lots of large rolls of paper everywhere, and there's even a massive printer in the corner (to handle the really big print jobs).
 *
 * Decorating here will be easy: they can make their own decorations. What you really need is a way to get further into the North Pole base while the elevators are offline.
 *
 * "Actually, maybe we can help with that," one of the Elves replies when you ask for help. "We're pretty sure there's a cafeteria on the other side of the back wall. If we could break through the wall, you'd be able to keep moving. It's too bad all of our forklifts are so busy moving those big rolls of paper around."
 *
 * If you can optimize the work the forklifts are doing, maybe they would have time to spare to break through the wall.
 *
 * The rolls of paper (@) are arranged on a large grid; the Elves even have a helpful diagram (your puzzle input) indicating where everything is located.
 *
 * For example:
 *
 * ..@@.@@@@.
 * @@@.@.@.@@
 * @@@@@.@.@@
 * @.@@@@..@.
 * @@.@@@@.@@
 * .@@@@@@@.@
 * .@.@.@.@@@
 * @.@@@.@@@@
 * .@@@@@@@@.
 * @.@.@@@.@.
 * The forklifts can only access a roll of paper if there are fewer than four rolls of paper in the eight adjacent positions. If you can figure out which rolls of paper the forklifts can access, they'll spend less time looking and more time breaking down the wall to the cafeteria.
 *
 * In this example, there are 13 rolls of paper that can be accessed by a forklift (marked with x):
 *
 * ..xx.xx@x.
 * x@@.@.@.@@
 * @@@@@.x.@@
 * @.@@@@..@.
 * x@.@@@@.@x
 * .@@@@@@@.@
 * .@.@.@.@@@
 * x.@@@.@@@@
 * .@@@@@@@@.
 * x.x.@@@.x.
 * Consider your complete diagram of the paper roll locations. How many rolls of paper can be accessed by a forklift?
 *
 *--- Part Two ---
 * Now, the Elves just need help accessing as much of the paper as they can.
 *
 * Once a roll of paper can be accessed by a forklift, it can be removed. Once a roll of paper is removed, the forklifts might be able to access more rolls of paper, which they might also be able to remove. How many total rolls of paper could the Elves remove if they keep repeating this process?
 *
 * Starting with the same example as above, here is one way you could remove as many rolls of paper as possible, using highlighted @ to indicate that a roll of paper is about to be removed, and using x to indicate that a roll of paper was just removed:
 *
 * Initial state:
 * ..@@.@@@@.
 * @@@.@.@.@@
 * @@@@@.@.@@
 * @.@@@@..@.
 * @@.@@@@.@@
 * .@@@@@@@.@
 * .@.@.@.@@@
 * @.@@@.@@@@
 * .@@@@@@@@.
 * @.@.@@@.@.
 *
 * Remove 13 rolls of paper:
 * ..xx.xx@x.
 * x@@.@.@.@@
 * @@@@@.x.@@
 * @.@@@@..@.
 * x@.@@@@.@x
 * .@@@@@@@.@
 * .@.@.@.@@@
 * x.@@@.@@@@
 * .@@@@@@@@.
 * x.x.@@@.x.
 *
 * Remove 12 rolls of paper:
 * .......x..
 * .@@.x.x.@x
 * x@@@@...@@
 * x.@@@@..x.
 * .@.@@@@.x.
 * .x@@@@@@.x
 * .x.@.@.@@@
 * ..@@@.@@@@
 * .x@@@@@@@.
 * ....@@@...
 *
 * Remove 7 rolls of paper:
 * ..........
 * .x@.....x.
 * .@@@@...xx
 * ..@@@@....
 * .x.@@@@...
 * ..@@@@@@..
 * ...@.@.@@x
 * ..@@@.@@@@
 * ..x@@@@@@.
 * ....@@@...
 *
 * Remove 5 rolls of paper:
 * ..........
 * ..x.......
 * .x@@@.....
 * ..@@@@....
 * ...@@@@...
 * ..x@@@@@..
 * ...@.@.@@.
 * ..x@@.@@@x
 * ...@@@@@@.
 * ....@@@...
 *
 * Remove 2 rolls of paper:
 * ..........
 * ..........
 * ..x@@.....
 * ..@@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@x.
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ...@@.....
 * ..x@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ...x@.....
 * ...@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ....x.....
 * ...@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ..........
 * ...x@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 * Stop once no more rolls of paper are accessible by a forklift. In this example, a total of 43 rolls of paper can be removed.
 *
 * Start with your original diagram. How many rolls of paper in total can be removed by the Elves and their forklifts?
 * */

fun main() {
    fun countAccessible(grid: List<CharArray>): Int {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        var accessibleCount = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '@') {
                    var adjacentCount = 0
                    for ((di, dj) in directions) {
                        val ni = i + di
                        val nj = j + dj
                        if (ni in grid.indices && nj in grid[i].indices && grid[ni][nj] == '@') {
                            adjacentCount++
                        }
                    }
                    if (adjacentCount < 4) {
                        accessibleCount++
                    }
                }
            }
        }
        return accessibleCount
    }

    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray() }
        return countAccessible(grid)
    }

    fun part2(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        val grid = Array(n) { i -> input[i].toCharArray() }
        val adj = Array(n) { IntArray(m) }
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == '@') {
                    var count = 0
                    for ((di, dj) in directions) {
                        val ni = i + di
                        val nj = j + dj
                        if (ni in 0 until n && nj in 0 until m && grid[ni][nj] == '@') {
                            count++
                        }
                    }
                    adj[i][j] = count
                }
            }
        }
        val queue = ArrayDeque<Pair<Int, Int>>()
        val inQueue = Array(n) { BooleanArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == '@' && adj[i][j] < 4) {
                    queue.add(i to j)
                    inQueue[i][j] = true
                }
            }
        }
        var removed = 0
        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeFirst()
            if (grid[i][j] != '@') continue
            grid[i][j] = '.'
            removed++
            for ((di, dj) in directions) {
                val ni = i + di
                val nj = j + dj
                if (ni in 0 until n && nj in 0 until m && grid[ni][nj] == '@') {
                    adj[ni][nj]--
                    if (adj[ni][nj] < 4 && !inQueue[ni][nj]) {
                        queue.add(ni to nj)
                        inQueue[ni][nj] = true
                    }
                }
            }
        }
        return removed
    }

    val testInput = listOf(
        "..@@.@@@@.",
        "@@@.@.@.@@",
        "@@@@@.@.@@",
        "@.@@@@..@.",
        "@@.@@@@.@@",
        ".@@@@@@@.@",
        ".@.@.@.@@@",
        "@.@@@.@@@@",
        ".@@@@@@@@.",
        "@.@.@@@.@."
    )
    println(part1(testInput)) // Esperado: 13
    println(part2(testInput)) // Esperado: 43

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
