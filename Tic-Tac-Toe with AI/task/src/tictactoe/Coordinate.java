package tictactoe;

class Coordinate {
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

    /*public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }*/

    public void setCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public boolean isLegal() {
        return 1 <= row && row <= 3 && 1 <= col && col <= 3;
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }
}
