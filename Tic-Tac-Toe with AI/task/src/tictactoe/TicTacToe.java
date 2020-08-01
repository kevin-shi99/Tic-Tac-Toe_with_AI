package tictactoe;

import java.util.Scanner;


/** Class TicTacToe, the prototype of the game
 *
 */
public class TicTacToe {

    /* ********* Fields ********** */

    private ChessBoard board;

    // Two players
    private Player playerX;
    private Player playerO;


    /* ********** API ************ */

    // Constructor
    public TicTacToe() {
        board = new ChessBoard();
    }


    public ChessBoard getBoard() {
        return board;
    }

    public Player PlayerX() {
        return playerX;
    }

    public Player PlayerO() {
        return playerO;
    }


    public void printCurrentBoard() {
        board.printBoard();
    }


    /** <p>This method process initial command.</p>
     * <p>Print out "Bad parameters" if user input is illegal. Continue to receive
     * input until user input is legal</p>
     * <p>Select players by arguments.</p>
     *
     */
    public void processInit() {
        Scanner scanner = new Scanner(System.in);

        boolean flag;

        do {
            flag = false;

            System.out.print("\nInput command: ");
            String[] args = scanner.nextLine().split("\\s+");

            // User input exit
            if (args.length == 1 && "exit".equals(args[0])) {
                System.exit(0);
            }

            if (args.length != 3) {
                System.out.println("Bad parameters!");
                flag = true;
            } else if (!"start".equals(args[0])) {
                System.out.println("Bad parameters!");
                flag = true;
            } else {
                playerX = Player.of(args[1]);
                playerO = Player.of(args[2]);

                if (playerX == null || playerO == null){
                    System.out.println("Bad parameters!");
                    flag = true;
                }
            }
        } while (flag);

    }


    /** Delegate player method
     *
     * @param player The player object who makes the move
     */
    public void makeMove(Player player) {
        player.makeMove(board);
    }

    public boolean gameContinues() {
        return board.checkWinner() == null;
    }

    public void printWinner() {
        Symbol winner = board.getWinner();
        if (winner == Symbol.X) {
            System.out.println("X wins");
        } else if (winner == Symbol.O) {
            System.out.println("O wins");
        } else if (winner == Symbol.EMPTY) {
            System.out.println("Draw");
        }
    }
}