package tictactoe

fun main() {
    val board = MutableList(3) { MutableList(3) {' '} }

    println("---------")
    println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
    println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
    println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
    println("---------")


    var xWin = false
    var oWin = false
    var draw = false
    var xTurn = true

    while (!xWin && !oWin && !draw) {
        try {
            print("Enter the coordinates:")
            val coordinates = readLine()!!.split(" ").map { it.toInt() }
            if (coordinates[0] !in 1..3 || coordinates[1] !in 1..3) {
                println("Coordinates should be from 1 to 3!")
            } else if (board[coordinates[0] - 1][coordinates[1] - 1].isLetter()) {
                println("This cell is occupied! Choose another one!")
            } else {
                    if (xTurn) {
                        board[coordinates[0] - 1][coordinates[1] - 1] = 'X'
                        xTurn = false
                    } else {
                        board[coordinates[0] - 1][coordinates[1] - 1] = 'O'
                        xTurn = true
                    }

                println("---------")
                println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
                println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
                println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
                println("---------")

            }
        } catch (e: Exception) {
            println("You should enter numbers!l")
        }



        var xCount = board.flatten().count { it == 'X' }
        var oCount = board.flatten().count { it == 'O' }

//Check Columns for Win

        for (col in 0..2) {
            when {
                (board[0][col] == board[1][col] && board[0][col] == board[2][col]) ->
                    if (board[0][col] == 'X') {
                        xWin = true
                    } else if (board[0][col] == 'O') {
                        oWin = true
                    }
            }
        }


//Check Rows for Win
        for (row in 0..2) {
            when {
                (board[row][0] == board[row][1] && board[row][0] == board[row][2]) ->
                    if (board[row][0] == 'X') {
                        xWin = true
                    } else if (board[row][0] == 'O') {
                        oWin = true
                    }
            }
        }

//Check Diagonal for Win
        when {
            (board[0][0] == board[1][1] && board[0][0] == board[2][2]) ->
                if (board[0][0] == 'X') {
                    xWin = true
                } else if (board[0][0] == 'O') {
                    oWin = true
                }

            (board[0][2] == board[1][1] && board[0][2] == board[2][0]) ->
                if (board[0][2] == 'X') {
                    xWin = true
                } else if (board[0][2] == 'O') {
                    oWin = true
                }
        }

        //Check for Draw
        if (xCount == 5 && oCount == 4 || xCount == 4 && oCount == 5 && !oWin && !xWin) {
            draw = true
        }

        //Announce Game End
        if (xWin && !oWin) {
            println("X wins")
        } else if (oWin && !xWin) {
            println("O wins")
        } else if (draw && !xWin && !oWin) {
            println("Draw")
        }
    }
}