Tic-Tac-Toe
===========

Implementation of the game Tic-Tac-Toe with varying board sizes and intelligent and random computer players.

User can select the type of players (human, machine, or random) and the board size (positive integer) desired. MachinePlayer uses the minimax algorithm to determine the best possible move.

To run, compile and execute:  

    java Game <player1> <player2> <size>  

\<player1\>, \<player2\>: "human", "machine", or "random"; player1 goes first.  
\<size\>: positive integer that specifies the width of the board.

###Playing as a HumanPlayer
To enter your next move, enter the x- and y- coordinates of the cell you want to take, separated by a space. For instance, to add a piece to the center cell in a 3x3 game:

    Coordinates of next move?
    1 1

Cells are indexed with 0 0 in the top-left corner, so that the board looks like:

    0 0     1 0     2 0     ...
    1 0     1 1     2 1     ...
    2 0     2 1     2 2     ...
    ...     ...     ...     

Pieces cannot be added to occupied spots or spots outside of the board.
    
