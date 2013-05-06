/**
 * Abstract class that interfaces with the Game class; should be extended by different types of
 * players such as Human, Random, and Machine.
 * @author	Kushal Ranjan
 * @version 050513
 */
abstract class Player {
	int type;
	Board board;
	
	/**
	 * General constructor that gives the Player a Board and a piece type.
	 * @param b	the board on which this player plays.
	 * @param t	type corresponding to Game.CIRCLE or Game.CROSS.
	 */
	Player(Board b, int t) {
		board = b;
		type = t;
	}
	
	/**
	 * makeMove() should select a move for this player and perform it. Board and Game do not throw
	 * exceptions if a move is invalid; however, an invalid addPiece() call will return false 
	 * instead of true. The Player subclass should respond accordingly.
	 */
	abstract void makeMove();
	
	/**
	 * Returns a String representation of this player.
	 */
	public String toString() {
		String strType = "";
		if(type == Game.CIRCLE)
			strType = "circles";
		else
			strType = "crosses";
		return  this.getClass().getName() + " (" + strType +")";
	}
	
}
