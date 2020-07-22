package tictactoe;

import java.util.Random;

/** Class computer
 * constructed by taking an argument of game (TicTacToe) object
 * Public method: makeMove()
 * private method: easyMove(), mediumMove(), hardMove()
 */
public class Computer {
    // Fields
    private GameDifficulty diff;

    final private TicTacToe game;

    private int number = 0;

    public Computer(TicTacToe thisGame) {
        this.diff = thisGame.getDiff();
        this.game = thisGame;
    }

    public Computer(TicTacToe thisGame, int number) {
        this.number = number;
        this.diff = thisGame.getDiff();
        this.game = thisGame;
    }

    public void makeMove() {
        switch (diff) {
            case EASY:
                easyMove();
                break;
            case MEDIUM:
                mediumMove();
                break;
            case HARD:
                hardMove();
                break;
        }
    }

    /** Computer takes move, level: easy
     * Each move is completely random.
     *
     */
    private void easyMove() {
        Random rand = new Random();

        boolean empty = false; // Is false if the generated coordinate is occupied
        int rndRow;
        int rndCol;

        char[][] currentBoard = game.getBoard();

        do {
            // Generate random integers from 0 to 2
            rndRow = rand.nextInt(3);
            rndCol = rand.nextInt(3);

            // Check if the generated coordinate is occupied

            if (currentBoard[rndRow][rndCol] == ' ') {
                empty = true;
            }
        } while (!empty);

        currentBoard[rndRow][rndCol] = move();

        game.setBoard(currentBoard);

        System.out.println("Making move level \"easy\"");
    }

    /** Computer takes move,
     * level: medium.
     * Not implemented
     */
    private void mediumMove() {
        System.out.println("Not implemented");
    }

    /** Computer takes move,
     * level: hard.
     * Not implemented
     */
    private void hardMove() {
        System.out.println("Not implemented");
    }

    /** Decide which side computer takes, based on the type of game
     *
     * @return char 'X', 'O' or ' '
     */
    private char move() {
        if (game.getType() == GameType.AI_USER) {
            game.setCntX(game.getCntX() + 1);
            return 'X';
        } else if (game.getType() == GameType.USER_AI) {
            game.setCntO(game.getCntO() + 1);
            return 'O';
        } else if (game.getType() == GameType.AI_AI) {
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
