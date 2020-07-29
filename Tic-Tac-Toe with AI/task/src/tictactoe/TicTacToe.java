package tictactoe;

import java.util.Scanner;

enum GameStates {
    ON, DRAW, X_WIN, O_WIN, OFF
}


/** Class TicTacToe, the prototype of the game
 *
 */
public class TicTacToe {
    // Field
    private char[][] board = new char[3][3];


    private int cntX = 0;
    private int cntO = 0;

    private GameStates states = GameStates.ON;

    private Coordinate userMove = new Coordinate();

    // Two players
    private Player player_1;
    private Player player_2;


    // Getters

    public GameStates getStates() {
        return states;
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

    public Player getPlayer_1() {
        return player_1;
    }

    public Player getPlayer_2() {
        return player_2;
    }

    public Player getPlayer(boolean first) {
        return player_1.isFirst() == first ? player_1 : player_2;
    }

    public Coordinate getUserMove() {
        return userMove;
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

        int column = -1;
        int row = -1;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the coordinates: ");
        String input = scanner.nextLine();

        String[] inputArray = input.split(" ");

        // Check if user input is text
        try {
            column = Integer.parseInt(inputArray[0]);
            row = Integer.parseInt(inputArray[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You should enter two numbers!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        } catch (Exception e) {
            System.out.println("You should enter two integers ranging from 1 to 3" +
                    " separated by a space!");
        }

        // Convert input coordinate to array coordinate used in the class

        userMove.setCoordinate(--row, --column);

        // Check if coordinate is in range
        if (!userMove.isLegal()) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        // Check whether the input coordinate is occupied or not
        if (board[row][column] == 'X' || board[row][column] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        // Has checked all possible errors, now the user input is legal
        return true;
    }

    private Player selectSecondPlayer(String command) {
        if ("user".equals(command)) {
            return new User(this);
        } else if ("easy".equals(command)) {
            return new EasyComputer(this);
        } else if ("medium".equals(command)) {
            return new MediumComputer(this);
        } else if ("hard".equals(command)) {
            return new HardComputer(this);
        } else {
            System.out.println("Bad parameters!");
            return null;
        }
    }

    /** Process initiate command.
     * Print out "Bad parameters" if user input is illegal
     * @return true if user input is legal anf false otherwise.
     */
    public boolean processInit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nInput command: ");
        String input = scanner.nextLine();

        String[] inputArray= input.split(" ");

        if (inputArray.length < 3) {
            if ("exit".equals(inputArray[0])) {
                this.states = GameStates.OFF;
                return true;
            } else {
                System.out.println("Bad parameters!");
                return false;
            }
        }

        if (!"start".equals(inputArray[0])) {
            System.out.println("Bad parameters!");
            return false;
        }

        // Determine the game type and game difficulty
        // Haven't been fully implemented
        switch (inputArray[1]) {
            case "user":
                player_1 = new User(this, true);

                player_2 = selectSecondPlayer(inputArray[2]);
                if (player_2 == null) {
                    return false;
                }

                player_2.setFirst(false);
                break;
            case "easy":
                player_1 = new EasyComputer(this, true);

                player_2 = selectSecondPlayer(inputArray[2]);
                if (player_2 == null) {
                    return false;
                }

                player_2.setFirst(false);
                break;
            case "medium":
                player_1 = new MediumComputer(this, true);

                player_2 = selectSecondPlayer(inputArray[2]);
                if (player_2 == null) {
                    return false;
                }

                player_2.setFirst(false);
                break;
            case "hard":
                player_1 = new HardComputer(this, true);

                player_2 = selectSecondPlayer(inputArray[2]);
                if (player_2 == null) {
                    return false;
                }

                player_2.setFirst(false);
                break;
            default:
                System.out.println("Bad parameters!");
                return false;
        }


        return true;
    }


    /** Check and update the state of te game (On, X wins, O wins or draw).
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

    public void clearGame() {

    }
}