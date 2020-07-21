package tictactoe;

import java.util.*;

enum GameStates {
    ON, DRAW, X_WIN, O_WIN
}

enum GameDifficulty {
    EASY, MEDIUM, HARD
}

enum  GameType {
    USER_USER, USER_AI, AI_USER, AI_AI
}

/** Class TicTacToe, the prototype of the game
 *
 */
class TicTacToe {
    // Field
    private char[][] board = new char[3][3];

    private int column; // User input column
    private int row; // User input row

    private int cntX = 0;
    private int cntO = 0;

    private GameStates states = GameStates.ON;
    private GameType type;
    private GameDifficulty diff;


    // Getters
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public GameStates getStates() {
        return states;
    }

    public GameType getType() {
        return type;
    }

    public GameDifficulty getDiff() {
        return diff;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getCntO() {
        return cntO;
    }

    public int getCntX() {
        return cntX;
    }

    // Setters
    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setCntO(int cntO) {
        this.cntO = cntO;
    }

    public void setCntX(int cntX) {
        this.cntX = cntX;
    }



    // Constructor
    public TicTacToe() {
        // Initialisation
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }




    /** Print current board **/
    public void printBoard() {
        System.out.println("---------");
        for (int i = 2; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    /** Get user input from keyboard and check if the user input is legal, print prompt if input is
     * illegal.
     * Illegal input includes: non-numerical input, one number, number out of bounds and occupied
     * coordinate.
     * @return true if input is legal and false if input is illegal or occupied
     */
    public boolean readCoordinates() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the coordinates: ");
        String input = scanner.nextLine();

        String[] inputArray = input.split(" ");

        // Check if user input is text
        try {
            column = Integer.parseInt(inputArray[0]);
            row = Integer.parseInt(inputArray[1]);
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        // Check if coordinate is in range
        if (column < 1 || column > 3 || row < 1 || row > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        // Convert input coordinate to array coordinate used in the class
        column -= 1;
        row -= 1;

        // Check whether the input coordinate is occupied or not
        if (board[row][column] == 'X' || board[row][column] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        // Has checked all possible errors, now the user input is legal
        return true;
    }

    /** Process initiate command.
     * Print out "Bad parameters" if user input is illegal
     * @return true if user input is legal anf false otherwise.
     */
    public boolean processInit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input command: ");
        String input = scanner.nextLine();

        String[] inputArray= input.split(" ");

        if (inputArray.length < 3) {
            System.out.println("Bad parameters!");
            return false;
        }

        if (!"start".equals(inputArray[0])) {
            System.out.println("Bad parameters!");
            return false;
        }

        // Determine the game type and game difficulty
        // Haven't been fully implemented
        switch (inputArray[1]) {
            case "user":
                if ("user".equals(inputArray[2])) {
                    type = GameType.USER_USER;
                } else if ("easy".equals(inputArray[2])) {
                    type = GameType.USER_AI;
                    diff = GameDifficulty.EASY;
                } else if ("medium".equals(inputArray[2])) {
                    type = GameType.USER_AI;
                    diff = GameDifficulty.MEDIUM;
                } else if ("hard".equals(inputArray[2])) {
                    type = GameType.USER_AI;
                    diff = GameDifficulty.HARD;
                } else {
                    System.out.println("Bad parameters!");
                    return false;
                }
                break;
            case "easy":
                if ("user".equals(inputArray[2])) {
                    type = GameType.AI_USER;
                    diff = GameDifficulty.EASY;
                } else if ("easy".equals(inputArray[2])) {
                    type = GameType.AI_AI;
                    diff = GameDifficulty.EASY;
                } else if ("medium".equals(inputArray[2])) {
                    type = GameType.AI_AI;
                    diff = GameDifficulty.EASY;
                } else {
                    System.out.println("Bad parameters!");
                    return false;
                }
                break;
            default:
                System.out.println("Bad parameters!");
                return false;
        }


        return true;
    }

    /** Check and update the state of te game (On, X wins, O wins or draw)
     * Alter the state field, print out the state of the game (X wins, O wins or Draw)
     */
    public void checkState() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'X') {
                    states = GameStates.X_WIN;
                    return;
                } else if (board[i][0] == 'O') {
                    states = GameStates.O_WIN;
                    return;
                }
            }
        }
        // check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                if (board[0][j] == 'X') {
                    states = GameStates.X_WIN;
                    return;
                } else if (board[0][j] == 'O') {
                    states = GameStates.O_WIN;
                    return;
                }
            }
        }
        // check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') {
                states = GameStates.X_WIN;
                return;
            } else if (board[0][0] == 'O') {
                states = GameStates.O_WIN;
                return;
            }
        } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') {
                states = GameStates.X_WIN;
                return;
            } else if (board[0][2] == 'O') {
                states = GameStates.O_WIN;
                return;
            }
        }

        if (cntX + cntO == 9) {
            states = GameStates.DRAW;
        }
    }

    public void printResult() {
        /* print out result (X wins, O wins or Draw) */
        if (states == GameStates.X_WIN) {
            System.out.println("X wins");
        } else if (states == GameStates.O_WIN) {
            System.out.println("O wins");
        } else if (states == GameStates.DRAW) {
            System.out.println("Draw");
        }
    }
}

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

/** Class computer
 * constructed by taking an argument of game (TicTacToe) object
 * Public method: makeMove()
 * private method: easyMove(), mediumMove(), hardMove()
 */
class Computer {
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
