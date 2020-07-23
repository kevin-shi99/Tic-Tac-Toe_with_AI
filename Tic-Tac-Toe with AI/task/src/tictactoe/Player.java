package tictactoe;

import java.util.Random;

/** <p><b>The base class of User and Computer.</b></p>
 * <br>
 * <p>Class fields:</p>
 * <li>game -- the game (TicTacToe) the player plays</li>
 * <li>boolean first -- describes if the player is the first to play or the second</li>
 * <br>
 * <p>Methods:</p>
 * <p>protected char move() -- returns 'X' if the player moves first, 'O' if the player moves
 * second</p>
 *
 */
public class Player {

    final protected TicTacToe game;

    protected boolean first;    // If player is the first to play, first is true

    protected int nextRow;

    protected int nextCol;      // the coordinate of player's next (predicted) move


    // Constructors
    public Player(TicTacToe thisGame) {
        this.game = thisGame;
    }

    public Player(TicTacToe thisGame, boolean first) {
        this.game = thisGame;
        this.first = first;
    }


    // Getters
    public boolean isFirst() {
        return first;
    }

    //Setters
    public void setFirst(boolean first) {
        this.first = first;
    }

    public void makeMove() {
        return;
    }


    /** This method decides which move ("X" or "O") to take.
     *
     * @return 'X" if the player is the first to move, 'O' if the player is the second to move
     */
    protected char move() {

        if (this.first == true) {
            game.setCntX(game.getCntX() + 1);
            return 'X';
        } else {
            game.setCntO(game.getCntO() + 1);
            return 'O';
        }

    }

    protected boolean isWinning(char[][] board) {
        // TODO: method isWinning and isLosing have bugs
        int i;
        int j;

        // Check rows
        for (i = 0; i < 3; i++) {
            int cntMove = 0;
            int emptyCol = -1;
            for (j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    emptyCol = j;
                } else if (board[i][j] == move()) {
                    cntMove++;
                }
            }

            if (cntMove == 2) {
                nextRow = i;
                nextCol = emptyCol;
                return true;
            }
        }

        // Check Columns
        for (j = 0; j < 3; j++) {
            int cntMove = 0;
            int emptyRow = -1;
            for (i = 0; i < 3; i++) {
                if (board[i][j] == ' ') {
                    emptyRow = i;
                } else if (board[i][j] == move()) {
                    cntMove++;
                }
            }

            if (cntMove == 2) {
                nextCol = j;
                nextRow = emptyRow;
                return true;
            }
        }

        // Check principle diagonal
        int cntMove = 0;
        int empty = -1;
        for (i = 0; i < 3; i++) {
            if (board[i][i] == ' ') {
                empty = i;
            } else if (board[i][i] == move()) {
                cntMove++;
            }
        }

        if (cntMove == 2) {
            nextRow = nextCol = empty;
            return true;
        }

        // Check counter-diagonal
        cntMove = 0;
        empty = -1;
        for (i = 0; i < 3; i++) {
            if (board[i][2 - i] == ' ') {
                empty = i;
            } else if (board[i][2 - i] == move()) {
                cntMove++;
            }
        }

        return false;
    }


    protected boolean isLosing(char[][] board) {
        Player opponent = game.getPlayer(!first);
        if (opponent.isWinning(board)) {
            return true;
        } else {
            return false;
        }
    }

}


class Computer extends Player{

    // Constructors
    public Computer(TicTacToe game) {
        super(game);
    }

    public Computer(TicTacToe game, boolean first) {
        super(game, first);
    }


    /** Computer take random move
     *
     */
    protected void randomMove() {

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
    }

}


class EasyComputer extends Computer {

    // Constructors
    public EasyComputer(TicTacToe game) {
        super(game);
    }

    public EasyComputer(TicTacToe game, boolean first) {
        super(game, first);
    }

    @Override
    public void makeMove() {
        randomMove();
        System.out.println("Making move level \"easy\"");
    }
}

// TODO: fix class MediumComputer
class MediumComputer extends Computer {

    // Fields
    int nextRow;
    int nextCol;    // Coordinate of next winning or defending move

    public MediumComputer(TicTacToe game) {
        super(game);
    }

    public MediumComputer(TicTacToe game, boolean first) {
        super(game, first);
    }

    @Override
    public void makeMove() {
        char[][] currentBoard = game.getBoard();

        if (isWinning(currentBoard)) {
            currentBoard[nextRow][nextCol] = move();
            game.setBoard(currentBoard);
        } else if (isLosing(currentBoard)) {
            currentBoard[nextRow][nextCol] = move();
            game.setBoard(currentBoard);
        } else {
            randomMove();
        }

        System.out.println("Making move level \"medium\"");
    }
}


class HardComputer extends Computer {

    public HardComputer(TicTacToe game) {
        super(game);
    }

    public HardComputer(TicTacToe game, boolean first) {
        super(game, first);
    }
}


class User extends Player {

    // Constructors
    public User(TicTacToe game) {
        super(game);
    }

    public User(TicTacToe game, boolean first) {
        super(game, first);
    }

    @Override
    public void makeMove() {
        int col = game.getColumn();
        int row = game.getRow();
        char[][] currentBoard = game.getBoard();

        currentBoard[row][col] = move();
    }
}


