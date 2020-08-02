# Tic-Tac-Toe with AI
A JatBrains Academy Project. Build a simple game "TicTacToe".

## Source Code
In the directory `/Tic-Tac-Toe with AI/task/src/tictactoe`.

Contains 8 classes, `Main.java`, `TicTacToe.java`, `Coordinate.java`, `ChessBoard.java`, `User.java`, `Computer.java`, `MediumComputer.java`, `HardComputer.java`, 1 interface `Player.java`, and 1 enum `Symbol.java`.

## How to play
Upon starting the program, it requires command input, you should input like below:
```
start easy medium
start user medium
start hard hard
```
, where `start` means "start game", `easy` and `medium` and `hard` stands for the difficulty of the computer opponent.

You can input `user` to play by yourself. Note that the sequence of input can affect whether you will play first.

For example:
```
start user medium //You will play first, that is to say you will play "X"
```

```
start hard user // You will play second, say "O"
```
## About the difficulty level
Currently this game only has "easy" level and "medium" level.

- **easy** means the computer will take each move randomly
- **medium** means if computer is going to will in one move, it will play the right move. If computer's opponent is going to win in one move, it will take the move (at least try its best to prevent it from happening. In other cases, computer takes random moves.
- **hard** mens the computer will take the best move in every situation. It uses the `minimax algorithm` to analyse the situation. You should be really carefull otherwise you will lose.

## Example
```
Input command: start user medium
---------
|       |
|       |
|       |
---------
Enter the coordinates: 2 2
---------
|       |
|   X   |
|       |
---------
Making move level "medium"
---------
|       |
|   X   |
| O     |
---------
Enter the coordinates: 1 3
---------
| X     |
|   X   |
| O     |
---------
Making move level "medium"
---------
| X     |
|   X   |
| O   O |
---------
Enter the coordinates: 2 1
---------
| X     |
|   X   |
| O X O |
---------
Making move level "medium"
---------
| X O   |
|   X   |
| O X O |
---------
Enter the coordinates: 1 2
---------
| X O   |
| X X   |
| O X O |
---------
Making move level "medium"
---------
| X O   |
| X X O |
| O X O |
---------
Enter the coordinates: 3 3
---------
| X O X |
| X X O |
| O X O |
---------
Draw
 
Input command: start medium user
---------
|       |
|       |
|       |
---------
Making move level "medium"
---------
|       |
|       |
|   X   |
---------
Enter the coordinates: 2 2
---------
|       |
|   O   |
|   X   |
---------
Making move level "medium"
---------
|       |
|   O   |
| X X   |
---------
Enter the coordinates: 3 1
---------
|       |
|   O   |
| X X O |
---------
Making move level "medium"
---------
| X     |
|   O   |
| X X O |
---------
Enter the coordinates: 1 2
---------
| X     |
| O O   |
| X X O |
---------
Making move level "medium"
---------
| X     |
| O O X |
| X X O |
---------
Enter the coordinates: 3 3
---------
| X   O |
| O O X |
| X X O |
---------
Making move level "medium"
---------
| X X O |
| O O X |
| X X O |
---------
Draw

Input commad: exit

Process finished with exit code 0
```
## Release
### Version 0.1
Level **hard** is now available. Download the `jar` file in the release page of this project.

*Note that you should have `Java Runtime Environment (JRE)` on your computer to run this program.*

#### How to run
**If you already have JRE or JDK on your computer, you can skip steps below.**
1. First, download JRE or [JDK](https://www.oracle.com/java/technologies/javase-downloads.html), latest version is suggested.
2. JDK version should be JDK 8 or above.
3. Don't forget to set the **environment variable** after installation in order to run the program in command line terminal.
4. You can refer to the [official documentation](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) to complete the installation of Java Environment.

**After you have installed the Java Environment**
1. Get into the directory which contains the jar file you've downloaded (probably in the system download folder).
2. Type in `java -jar TicTacToe_v-0.1.jar`
3. `Input commad: ` should appear in your command line.
4. Now you can play this game, type in `exit` or use `ctrl+C` to exit the program after you get tired of playing this.
