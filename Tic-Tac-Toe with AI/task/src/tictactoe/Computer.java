package tictactoe;

import java.util.Random;

abstract class Computer implements Player{
    protected void randomMove(ChessBoard board) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3) + 1;
            col = rand.nextInt(3) + 1;
        } while (board.isFull(row, col));

        board.setBoard(row, col);
    }
}

class EasyComputer extends Computer {

    @Override
    public void makeMove(ChessBoard board) {
        randomMove(board);

        System.out.println("Making move level \"easy\"");
    }
}