Program that reads input from user in terminal.
To determine possible placements of ships in the board game battleship.

Trying to do this using backtrack technique and recursive function.

___________________________________________

The first line of input contains two space-separated integers 'n' and 'k'.
Which represent a game of Mini Battleship played on an n x n board with 'k' ships.

Each of the next lines contains a string s. This is what you sees of your opponent's board.
A character ‘X’ represents one of Bob’s shots that missed.
A character ‘O’ (Letter O, not zero) represents one of Bob’s shots that hit.
A dot (‘.’) represents a square where Bob has not yet taken a shot.

Each of the next 'k' lines contains a single integer 'x'. These are the sizes of the ships.


Output a single integer, which is the number of ways the 'k' distinct ships could be placed on your opponent's board.


Sample input:
4 3  (4 = board size 4x4, 3 = number of ships)
....
.OX.  (what you currently see of the board)
....
O..X
3 (size of ship)
2
1

Sample output(possible placements):
264
