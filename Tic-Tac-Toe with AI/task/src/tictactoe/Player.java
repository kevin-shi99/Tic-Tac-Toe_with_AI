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

    }


    /** This method decides which move ("X" or "O") to take.
     *
     * @return 'X" if the player is the first to move, 'O' if the player is the second to move
     */
    protected char move() {

        if (this.first) {
            return 'X';
        } else {
            return 'O';
        }

    }

    /** <p>This method determine if the player that calls this method is going to win in one move
     * .</p>
     * <p>Returns the Coordinate of winning move if is is winning, returns null if is is not
     * going to win.</p>
     * @param board  char[][]
     * @return boolean
     */
    protected Coordinate winningMove(char[][] board) {
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

            // emptyCol != -1 to ensure three cells are all occupied
            if (cntMove == 2 && emptyCol != -1) {
                return new Coordinate(i, emptyCol);
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


            if (cntMove == 2 && emptyRow != -1) {
                return new Coordinate(emptyRow, j);
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

        if (cntMove == 2 && empty != -1) {
            return new Coordinate(empty, empty);
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

            if (cntMove == 2 && empty != -1) {
                return new Coordinate(empty, 2 - empty);
            }
        }

        return null;
    }


    protected Coordinate losingMove(char[][] board) {

        Player opponent = game.getPlayer(!first);   // Select the opponent by inverting the first

        return opponent.winningMove(board);
    }


    protected void countMove() {
        if (move() == 'X') {
            game.setCntX(game.getCntX() + 1);
        } else if (move() == 'O') {
            game.setCntO(game.getCntO() + 1);
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
        countMove();
        System.out.println("Making move level \"easy\"");
    }
}

class MediumComputer extends Computer {

    public MediumComputer(TicTacToe game) {
        super(game);
    }

    public MediumComputer(TicTacToe game, boolean first) {
        super(game, first);
    }

    @Override
    public void makeMove() {
        char[][] currentBoard = game.getBoard();

        Coordinate nextMove = this.winningMove(currentBoard);

        if (nextMove != null) {
            currentBoard[nextMove.getRow()][nextMove.getCol()] = move();
            game.setBoard(currentBoard);
        } else {
            nextMove = this.losingMove(currentBoard);
            if (nextMove != null) {
                currentBoard[nextMove.getRow()][nextMove.getCol()] = move();
                game.setBoard(currentBoard);
            } else {
                randomMove();
            }
        }

        countMove();

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
        Coordinate move = game.getUserMove();

        char[][] currentBoard = game.getBoard();

        currentBoard[move.getRow()][move.getCol()] = move();

        game.setBoard(currentBoard);
    }
}


