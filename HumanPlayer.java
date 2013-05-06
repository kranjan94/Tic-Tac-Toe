import java.io.*;
/**
 * Player class that allows user to play. Takes in coordinates before each HumanPlayer move.
 * @author	Kushal Ranjan
 * @version 050513
 */
public class HumanPlayer extends Player {
	HumanPlayer(Board b, int t) {
		super(b, t);
	}
	
	/**
	 * Takes in two integers within the range of the board and makes a move using those integers
	 * as x- and y-coordinates. If invalid coordinates are given, input is requested again.
	 * 
	 * Format of move:
	 * [x] [y]
	 * 0 <= x < board.size
	 * 0 <= y < board.size
	 */
	void makeMove() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Coordinates of next move?");
		String input = "";
		try {
			input = in.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String[] coords = input.split(" ");
		if(coords.length != 2) { // 0, 1, or more than 2 coordinates given
			System.out.println("Invalid move format. Format should be '<x> <y>'.");
			makeMove();
			return;
		}
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]); 
		if(x >= board.size || y >= board.size || x < 0 || y < 0) { //Coordinate not in board
			System.out.println("Invalid coordinates. Coordinates must be between 0 and " 
						+ (board.size-1));
			makeMove();
		} else {
			if(!board.addPiece(type, x, y)){ //Coordinates are of occupied space on board
				System.out.println("Space already taken.");
				makeMove();
			}
		}
	}
}
