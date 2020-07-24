# Tic-Tac-Toe with AI
A JatBrains Academy Project. Build a simple game "TicTacToe".

## Source Code
In the directory `/Tic-Tac-Toe with AI/task/src/tictactoe`.

Contains four classes, `Main.java`, `Player.java`, `TicTacToe.java`, `Coordinate.java`.

## How to play
Upon starting the program, it requires command input, you should input like below:
```
start easy medium
start user medium
```
, where `start` means "start game", `easy` and `medium` stands for the difficulty of the computer opponent.

You can input `user` to play by yourself. Note that the sequence of input can affect whether you will play first.

For example:
```
start user medium //You will play first, that is to say you will play "X"
```

```
start easy user // You will play second, say "O"
```
## About the difficulty level
Currently this game only has "easy" level and "medium" level.

- **easy** means the computer will take each move randomly
- **medium** means if computer is going to will in one move, it will play the right move. If computer's opponent is going to win in one move, it will take the move (at least try its best to prevent it from happening. In other cases, computer takes random moves.

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
 
```
