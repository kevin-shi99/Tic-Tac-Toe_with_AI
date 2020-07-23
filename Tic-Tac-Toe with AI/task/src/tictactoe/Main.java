package tictactoe;

public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        while (!game.processInit())
            ;
        game.printBoard();

        startGame(game.getPlayer_1(), game.getPlayer_2(), game);
        game.printResult();
    }

    private static void startGame(Player player1, Player player2, TicTacToe game) {
        player1.setFirst(true);
        player2.setFirst(false);

        while (game.getStates() == GameStates.ON) {
            if (player1 instanceof User) {
                while (!game.readCoordinates())
                    ;
            }
            player1.makeMove();
            game.printBoard();
            game.checkState();
            if (game.getStates() != GameStates.ON) break;

            if (player2 instanceof User) {
                while (!game.readCoordinates())
                    ;
            }
            player2.makeMove();
            game.printBoard();
            game.checkState();
            if (game.getStates() != GameStates.ON) break;
        }
    }

}
