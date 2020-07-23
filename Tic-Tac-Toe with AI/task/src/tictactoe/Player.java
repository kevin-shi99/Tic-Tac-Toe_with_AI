package tictactoe;

import java.util.Random;

/** The base class of User and Computer of all difficulty
 *
 */
public class Player {

    final protected TicTacToe game;

    protected boolean first;


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


    protected char move() {
        if (this.first == true) {
            game.setCntX(game.getCntX() + 1);
            return 'X';
        } else {
            game.setCntO(game.getCntO() + 1);
            return 'O';
        }
    }
}



class EasyComputer extends Player {

    // Constructors
    public EasyComputer(TicTacToe game) {
        super(game);
    }

    public EasyComputer(TicTacToe game, boolean first) {
        super(game, first);
    }

    @Override
    public void makeMove() {
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
}


class MediumComputer extends Player {

    public MediumComputer(TicTacToe game) {
        super(game);
    }

    public MediumComputer(TicTacToe game, boolean first) {
        super(game, first);
    }
}


class HardComputer extends Player {

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


