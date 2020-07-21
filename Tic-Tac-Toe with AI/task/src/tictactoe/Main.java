package tictactoe;

public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        while (!game.processInit())
            ;
        game.printBoard();

        if (game.getType() == GameType.AI_AI) {
            Computer ai_1 = new Computer(game, 1);
            Computer ai_2 = new Computer(game, 2);

            while (game.getStates() == GameStates.ON) {
                ai_1.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;

                ai_2.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;
            }
        } else if (game.getType() == GameType.USER_USER) {
            User player_1 = new User(game, 1);
            User player_2 = new User(game, 2);

            while (game.getStates() == GameStates.ON) {
                while (!game.readCoordinates())
                    ;
                player_1.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;

                while (!game.readCoordinates())
                    ;
                player_2.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;
            }
        } else if (game.getType() == GameType.AI_USER) {
            Computer ai_1 = new Computer(game);
            User player_2 = new User(game);

            while (game.getStates() == GameStates.ON) {
                ai_1.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;

                while (!game.readCoordinates())
                    ;
                player_2.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;
            }
        } else if (game.getType() == GameType.USER_AI) {
            User player_1 = new User(game);
            Computer ai_2 = new Computer(game);

            while (game.getStates() == GameStates.ON) {
                while (!game.readCoordinates())
                    ;
                player_1.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;

                ai_2.makeMove();
                game.printBoard();
                game.checkState();
                if (game.getStates() != GameStates.ON) break;
            }
        }
        game.printResult();
    }

}
