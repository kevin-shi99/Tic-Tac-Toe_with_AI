package tictactoe;




interface Player {
    /** Select the type of the player
     *
     * @param level the difficulty of the game, including easy, medium and hard.
     * @return The Player object. Return null if the level is unknown.
     */
    static Player of(String level) {
        switch (level) {
            case "easy":
                return new EasyComputer();
            case "medium":
                return new MediumComputer();
            case "hard":
                return new HardComputer();
            case "user":
                return new User();
            default:
                return null;
        }
    }

    void makeMove(ChessBoard board);
}

