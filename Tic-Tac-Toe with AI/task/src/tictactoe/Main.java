package tictactoe;

public class Main {

    public static void main(String[] args) {
        while (true) {
            TicTacToe game = new TicTacToe();
            game.processInit();
            game.printCurrentBoard();
            do {
                game.makeMove(game.PlayerX());
                game.printCurrentBoard();
                if (!game.gameContinues()) break;
                game.makeMove(game.PlayerO());
                game.printCurrentBoard();
            } while (game.gameContinues());
            game.printWinner();
        }
    }
}
