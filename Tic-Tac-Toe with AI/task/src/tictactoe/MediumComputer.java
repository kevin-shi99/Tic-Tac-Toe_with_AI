package tictactoe;

class MediumComputer extends Computer {

    Coordinate nextMove;

    public MediumComputer() {
        nextMove = new Coordinate();
    }

    @Override
    public void makeMove(ChessBoard board) {
        if (isWinning(board, board.nextMove()) || isLosing(board, board.nextMove())) {
            board.setBoard(nextMove);
        } else {
            randomMove(board);
        }

        System.out.println("Making move level \"medium\"");
    }

    /** <p>Check whether the MediumComputer is going to win in one move.</p>
     * <p>If it is going to win, store the winning move in the field nextMove.</p>
     * @return true if it is going to win in one move, false otherwise.
     */
    private boolean isWinning(ChessBoard board, Symbol symbol) {
        Coordinate[] result = {
                checkRows(board, symbol),
                checkColumns(board, symbol),
                checkPrincipleDiagonal(board, symbol),
                checkCounterDiagonal(board, symbol)
        };

        for (Coordinate coordinate :
                result) {
            if (coordinate != null) {
                nextMove = coordinate;
                return true;
            }
        }

        return false;
    }

    /** <p>Check whether the MediumComputer is going to lose in one move.</p>
     * Opposite to isWinning().
     * @return true if it is going to lose in one move, false otherwise.
     */
    private boolean isLosing(ChessBoard board, Symbol symbol) {
        return isWinning(board, symbol.opposite());
    }

    private Coordinate checkRows(ChessBoard board, Symbol symbol) {
        Coordinate empty;
        for (int row = 1; row < 4; row++) {
            int countFullCells = 0;
            empty = new Coordinate(-1, -1);
            for (int col = 1; col < 4; col++) {
                if (board.getSymbol(row, col) == symbol) {
                    countFullCells++;
                } else if (board.isEmpty(row, col)) {
                    empty.setCoordinate(row, col);
                }
            }

            if (countFullCells == 2 && !empty.equals(-1, -1)) {
                return empty;
            }
        }

        return null;
    }

    private Coordinate checkColumns(ChessBoard board, Symbol symbol) {
        Coordinate empty;
        for (int col = 1; col < 4; col++) {
            int countFullCells = 0;
            empty = new Coordinate(-1, -1);
            for (int row = 1; row < 4; row++) {
                if (board.getSymbol(row, col) == symbol) {
                    countFullCells++;
                } else if (board.isEmpty(row, col)) {
                    empty.setCoordinate(row, col);
                }
            }

            if (countFullCells == 2 && !empty.equals(-1, -1)) {
                return empty;
            }
        }

        return null;
    }

    private Coordinate checkPrincipleDiagonal(ChessBoard board, Symbol symbol) {
        Coordinate empty = new Coordinate(-1, -1);
        int countFullCells = 0;

        for (int i = 1; i < 4; i++) {
            if (board.getSymbol(i, i) == symbol) {
                countFullCells++;
            } else if (board.isEmpty(i, i)) {
                empty.setCoordinate(i, i);
            }
        }

        if (countFullCells == 2 && !empty.equals(-1, -1)) {
            return empty;
        } else return null;
    }

    private Coordinate checkCounterDiagonal(ChessBoard board, Symbol symbol) {
        Coordinate empty = new Coordinate(-1, -1);
        int countFullCells = 0;

        for (int i = 1; i < 4; i++) {
            if (board.getSymbol(i, 4 - i) == symbol) {
                countFullCells++;
            } else if (board.isEmpty(i, 4 - i)) {
                empty.setCoordinate(i, 4 - i);
            }
        }

        if (countFullCells == 2 && !empty.equals(-1, -1)) {
            return empty;
        } else return null;
    }

}
