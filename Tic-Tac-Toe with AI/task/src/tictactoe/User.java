package tictactoe;

import java.util.Scanner;

class User implements Player {
    private final Coordinate nextMove;

    public User() {
        this.nextMove = new Coordinate();
    }

    /** <p>User makes move.</p>
     * <p>Implements the 'makeMove()' method in <b>interface Player</b></p>
     * <p>First prompt to read in user input coordinate, then check if the cell to
     * make the move is empty. If not, prompt to receive new input, until the move is
     * legal.</p>
     * @param board the board (obj ChessBoard) where the user wants to make move.
     * @see ChessBoard
     * @see Player
     */
    @Override
    public void makeMove(ChessBoard board) {
        boolean flag;
        do {
            flag = false;
            readCoordinates(); // loop until have valid input
            // Check if the move is empty
            if (board.isFull(nextMove)) {
                System.out.println("This cell is occupied, choose another one.");
                flag = true;
            }
        } while (flag);
        board.setBoard(nextMove);
    }

    private void readCoordinates() {
        Scanner scanner = new Scanner(System.in);

        int column = -1;
        int row = -1;

        boolean flag;

        do {
            flag = false;
            System.out.print("Enter the coordinates: ");

            String[] args = scanner.nextLine().split("\\s+");

            // Check if user input is text
            try {
                column = Integer.parseInt(args[0]);
                row = Integer.parseInt(args[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You should enter two numbers!");
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                flag = true;
            } catch (Exception e) {
                System.out.println("You should enter two integers ranging from 1 to 3" +
                        " separated by a space!");
                flag = true;
            }

            nextMove.setCoordinate(row, column);

            // Check if coordinate is in range
            if (!nextMove.isLegal()) {
                System.out.println("Coordinates should be from 1 to 3!");
                flag = true;
            }
        } while (flag);
    }
}