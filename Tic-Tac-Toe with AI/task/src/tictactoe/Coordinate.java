package tictactoe;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate() {
        this.row = -1;
        this.col = -1;
    }

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public boolean isLegal() {
        if (0 <= row && row <= 2 && 0 <= col && col <= 2) {
            return true;
        } else {
            return false;
        }
    }
}
