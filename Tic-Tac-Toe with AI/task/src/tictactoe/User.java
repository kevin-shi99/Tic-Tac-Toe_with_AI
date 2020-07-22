package tictactoe;

/** Class user
 * constructed by taking an argument of game (TicTacToe) object
 * public method: makeMove()
 */
class User {
    final private TicTacToe game;

    private int number = 0;

    public User(TicTacToe thisGame) {
        this.game = thisGame;
    }

    public User(TicTacToe thisGame, int number) {
        this.number = number;
        this.game = thisGame;
    }

    public void makeMove() {
        int col = game.getColumn();
        int row = game.getRow();
        char[][] currentBoard = game.getBoard();

        currentBoard[row][col] = move();
    }

    private char move() {
        if (game.getType() == GameType.AI_USER) {
            game.setCntO(game.getCntO() + 1);
            return 'O';
        } else if (game.getType() == GameType.USER_AI) {
            game.setCntX(game.getCntX() + 1);
            return 'X';
        } else if (game.getType() == GameType.USER_USER) {
            if (number == 1) {
                game.setCntX(game.getCntX() + 1);
                return 'X';
            } else if (number == 2) {
                game.setCntO(game.getCntO() + 1);
                return 'O';
            }
        }

        return ' ';
    }
}