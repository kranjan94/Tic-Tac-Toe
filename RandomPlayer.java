/**
 * Basic automated player that simply makes random move choices.
 * @author	Kushal Ranjan
 * @version 050513
 */
class RandomPlayer extends Player {
	RandomPlayer(Board b, int t) {
		super(b, t);
	}

	/**
	 * Randomly selects a move and makes it; if the move was unsuccessful, randomly selects
	 * another move until a move is successful.
	 */
	void makeMove() {
		boolean moveMade = false;
		while(!moveMade) { //Stop once a move is successful
			int randX = (int)(Math.random() * board.size);
			int randY = (int)(Math.random() * board.size);
			moveMade = board.addPiece(type, randX, randY);
		}
	}
}
