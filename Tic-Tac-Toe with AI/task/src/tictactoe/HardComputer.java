package tictactoe;

import java.util.Vector;

class HardComputer extends Computer {
    @Override
    public void makeMove(ChessBoard board) {

        Vector<Coordinate> possibleMoves = new Vector<>();
        // Get all possible moves
        for (int row = 1; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                if (board.isEmpty(row, col)) {
                    possibleMoves.add(new Coordinate(row, col));
                }
            }
        }

        Coordinate bestMove = null;
        boolean isMaximizing = board.nextMove() == Symbol.X;

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            int score;
            for (Coordinate move :
                    possibleMoves) {
                board.setBoard(move);
                score = minimax(board, false);
                board.unset(move);
                if (score > maxScore) {
                    maxScore = score;
                    bestMove = move;
                }
            }
        } else {
            int minScore = Integer.MAX_VALUE;
            int score;
            for (Coordinate move :
                    possibleMoves) {
                board.setBoard(move);
                score = minimax(board, true);
                board.unset(move);
                if (score < minScore) {
                    minScore = score;
                    bestMove = move;
                }
            }
        }

        board.setBoard(bestMove);

        System.out.println("Making move level \"hard\"");
    }

    private int minimax(ChessBoard board, boolean isMaximizing) {

        Symbol winner = board.checkWinner();
        if (winner == Symbol.X) {
            return 1;
        } else if (winner == Symbol.O) {
            return -1;
        } else if (winner == Symbol.EMPTY) {
            return 0;
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            int score;
            for (int row = 1; row < 4; row++) {
                for (int col = 1; col < 4; col++) {
                    if (board.isEmpty(row, col)) {
                        board.setBoard(row, col);
                        score = minimax(board, false);
                        maxScore = Math.max(score, maxScore);
                        board.unset(row, col);
                    }
                }
            }

            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            int score;
            for (int row = 1; row < 4; row++) {
                for (int col = 1; col < 4; col++) {
                    if (board.isEmpty(row, col)) {
                        board.setBoard(row, col);
                        score = minimax(board, true);
                        minScore = Math.min(score, minScore);
                        board.unset(row, col);
                    }
                }
            }

            return minScore;
        }
    }

}
