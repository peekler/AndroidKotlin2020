package hu.ecity.tictactoe.model

object TicTacToeModel {

    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY)
    )

    private var nextPlayer = CIRCLE

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, player: Short) {
        model[x][y] = player
    }

    fun getNextPlayer() = nextPlayer

    fun changePlayer() {
        nextPlayer =
            if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

    fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
    }


}