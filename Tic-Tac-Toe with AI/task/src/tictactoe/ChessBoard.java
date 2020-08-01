package tictactoe;

import java.util.Arrays;

class ChessBoard {
    // Fields
    private Symbol[][] board;

    private boolean xIsNext;

    private Symbol winner;

    private int countMove;



    /* ******************* API ****************** */

    public ChessBoard() {
        board = new Symbol[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Symbol.EMPTY;
            }
        }

        xIsNext = true;
        winner = null;
        countMove = 0;
    }


    public Symbol getWinner() {
        return winner;
    }

    public void setBoard(int row, int col) {
        checkRowCol(row, col);
        if (xIsNext)
            board[row - 1][col - 1] = Symbol.X;
        else
            board[row - 1][col - 1] = Symbol.O;

        nextSymbol();
        countMove++;
    }

    public void setBoard(Coordinate point) {
        setBoard(point.getRow(), point.getCol());
    }

    public void unset(int row, int col) {
        checkRowCol(row, col);
        if (isFull(row, col)) {
            board[row - 1][col - 1] = Symbol.EMPTY;
        }
        countMove--;
    }

    public void unset(Coordinate point) {
        unset(point.getRow(), point.getCol());
    }

    public boolean isEmpty(int row, int col) {
        checkRowCol(row, col);
        return board[row - 1][col - 1] == Symbol.EMPTY;
    }

    public boolean isEmpty(Coordinate point) {
        return isEmpty(point.getRow(), point.getCol());
    }

    public boolean isX(int row, int col) {
        checkRowCol(row, col);
        return board[row - 1][col - 1] == Symbol.X;
    }

    public boolean isX(Coordinate point) {
        return isX(point.getRow(), point.getCol());
    }

    public boolean isO(int row, int col) {
        checkRowCol(row, col);
        return board[row - 1][col - 1] == Symbol.O;
    }

    public boolean isO(Coordinate point) {
        return isO(point.getRow(), point.getCol());
    }

    public boolean isFull(int row, int col) {
        return isX(row, col) || isO(row, col);
    }

    public boolean isFull(Coordinate point) {
        return isFull(point.getRow(), point.getCol());
    }

    public Symbol getSymbol(int row, int col) {
        checkRowCol(row, col);
        return board[row - 1][col - 1];
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 3; i > 0; i--) {
            System.out.print("| ");
            for (int j = 1; j < 4; j++) {
                System.out.print(toString(i, j) + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    Symbol nextMove() {
        return xIsNext ? Symbol.X : Symbol.O;
    }

    /** This method checks the status of the game.
     *
     * @return Symbol.X if XPlayer wins the game, return Symbol.O if OPlayer wins the
     * game, return Symbol.EMPTY if is a draw, return null if game is not finished.
     * @see Symbol
     */
    public Symbol checkWinner() {
        if (checkRows(Symbol.X) || checkCols(Symbol.X) || checkDiagonals(Symbol.X)) {
            winner = Symbol.X;
            return Symbol.X;
        } else if (checkRows(Symbol.O) || checkCols(Symbol.O) || checkDiagonals(Symbol.O)) {
            winner = Symbol.O;
            return Symbol.O;
        } else if (countMove == 9) {
            winner = Symbol.EMPTY;
            return Symbol.EMPTY;    // EMPTY stands for draw
        } else {
            winner = null;
            return null;    // Game not finished
        }
    }



    /* ********************* Private Methods ********************* */

    // Check if the input argument is legal
    private void checkRowCol(int row, int col) {
        if (row < 1 || row > 3 || col < 1 || col > 3) {
            throw new IllegalArgumentException();
        }
    }

    private void nextSymbol() {
        xIsNext = !xIsNext;
    }

    /** This method checks if one row is full.
     *
     * @param symbol the symbol you want to check (X or O).
     * @return true if one row is full, false if non of the rows is full.
     */
    private boolean checkRows(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        return false;
    }

    /** This method checks if one column is full.
     *
     * @param symbol the symbol you want check (X or O).
     * @return true if one col is full, false if non of the cols is full.
     */
    private boolean checkCols(Symbol symbol) {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPrincipleDiagonal(Symbol symbol) {
        return board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol;
    }

    private boolean checkCounterDiagonal(Symbol symbol) {
        return board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol;
    }

    private boolean checkDiagonals(Symbol symbol) {
        return checkPrincipleDiagonal(symbol) || checkCounterDiagonal(symbol);
    }

    private String toString(int row, int col) {
        if (getSymbol(row, col) == Symbol.X) {
            return "X";
        } else if (getSymbol(row, col) == Symbol.O) {
            return "O";
        } else {
            return " ";
        }
    }


}
