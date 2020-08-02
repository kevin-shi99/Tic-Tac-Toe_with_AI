package tictactoe;

class ChessBoard {

    private final int SIZE = 3; // The size of the board is 3 * 3

    // Fields

    private final Symbol[] board;

    private boolean xIsNext;

    private Symbol winner;

    private int countMove;

    /* ******************* API ****************** */

    public ChessBoard() {
        board = new Symbol[SIZE * SIZE];

        for (int i = 0; i < 9; i++) {
            board[i] = Symbol.EMPTY;

        }

        xIsNext = true;
        winner = null;
        countMove = 0;
    }

    public int toIndex(int row, int col) {
        checkRowCol(row, col);
        return (row - 1) * SIZE + col - 1;
    }

    public Symbol getWinner() {
        return winner;
    }

    public void setBoard(int row, int col) {
        checkRowCol(row, col);
        if (xIsNext)
            board[toIndex(row, col)] = Symbol.X;
        else
            board[toIndex(row, col)] = Symbol.O;

        nextSymbol();
        countMove++;
    }

    public void setBoard(Coordinate point) {
        setBoard(point.getRow(), point.getCol());
    }

    public void unset(int row, int col) {
        checkRowCol(row, col);
        if (isFull(row, col)) {
            board[toIndex(row, col)] = Symbol.EMPTY;
        }
        nextSymbol();
        countMove--;
    }

    public void unset(Coordinate point) {
        unset(point.getRow(), point.getCol());
    }

    public boolean isEmpty(int row, int col) {
        checkRowCol(row, col);
        return board[toIndex(row, col)] == Symbol.EMPTY;
    }

    public boolean isEmpty(Coordinate point) {
        return isEmpty(point.getRow(), point.getCol());
    }

    public boolean isX(int row, int col) {
        checkRowCol(row, col);
        return board[toIndex(row, col)] == Symbol.X;
    }

    public boolean isX(Coordinate point) {
        return isX(point.getRow(), point.getCol());
    }

    public boolean isO(int row, int col) {
        checkRowCol(row, col);
        return board[toIndex(row, col)] == Symbol.O;
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
        return board[toIndex(row, col)];
    }

    public void printBoard() {
        System.out.println("---------");
        for (int row = 3; row > 0; row--) {
            System.out.print("| ");
            for (int col = 1; col < 4; col++) {
                System.out.print(toString(row, col) + " ");
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
        for (int row = 1; row < 4; row++) {
            if (board[toIndex(row, 1)] == symbol && board[toIndex(row, 2)] == symbol
                    && board[toIndex(row,3)] == symbol) {
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
        for (int col = 1; col < 4; col++) {
            if (board[toIndex(1, col)] == symbol && board[toIndex(2, col)] == symbol
                    && board[toIndex(3, col)] == symbol) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPrincipleDiagonal(Symbol symbol) {
        return board[toIndex(1, 1)] == symbol && board[toIndex(2, 2)] == symbol
                && board[toIndex(3, 3)] == symbol;
    }

    private boolean checkCounterDiagonal(Symbol symbol) {
        return board[toIndex(1, 3)] == symbol && board[toIndex(2, 2)] == symbol
                && board[toIndex(3, 1)] == symbol;
    }

    private boolean checkDiagonals(Symbol symbol) {
        return checkPrincipleDiagonal(symbol) || checkCounterDiagonal(symbol);
    }

    private String toString(int row, int col) {
        if (getSymbol(row, col) == Symbol.X) {
            return Symbol.X.getSymbol();
        } else if (getSymbol(row, col) == Symbol.O) {
            return Symbol.O.getSymbol();
        } else {
            return Symbol.EMPTY.getSymbol();
        }
    }

}
