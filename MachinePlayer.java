import list.*;
/**
 * Player class that makes planned moves using the minimax move selection algorithm instead of
 * random moves.
 * @author	Kushal Ranjan
 * @version 050513
 */
class MachinePlayer extends Player {
	
	MachinePlayer(Board b, int t) {
		super(b, t);
	}
	
	/**
	 * Determines the optimal move using the minimax algorithm and executes it.
	 */
	void makeMove() {
		Move bestMove = chooseMove(type, 0).m;
		board.addPiece(type, bestMove.x, bestMove.y);
	}
	
	/**
	 * Performs the minimax algorithm to determine an optimal move.
	 * @param player	current player being checked in minimax
	 * @param numMoves	the number of moves it takes to get to this board
	 * @return			the best Best for this player
	 */
	public Best chooseMove(int player, int numMoves) {
		Best myBest = new Best(null, 0);
		Best reply;
		
		//Base case of minimax; game finished
		if(board.hasWin(type)) { 
			return new Best(null, 10/numMoves);
		} else if(board.hasWin(Game.otherPlayer(type))) {
			return new Best(null, -10/numMoves);
		} else if(!board.hasEmptySpaces()) {
			return new Best(null, 0);
		}
		
		if(player == type) {
			myBest.score = Integer.MIN_VALUE;
		} else {
			myBest.score = Integer.MAX_VALUE;
		}
		
		Iterator<Move> moves = getPossibleMoves(board, player).iterator();
		while(moves.hasNext()) { //Iterate over possible moves
			Move m = moves.next();
			board.addPiece(player, m.x, m.y);
			reply = chooseMove(Game.otherPlayer(player), numMoves + 1);
			board.undoAdd(m.x, m.y);
			if((player == type && reply.score >= myBest.score) //New best move found
					|| (player == Game.otherPlayer(type) && reply.score <= myBest.score)) {
						myBest.m = m;
						myBest.score = reply.score;
					}
		}
		return myBest;
	}
	
	/**
	 * Return all possible Moves for the specified player on the given board.
	 * @param player	player for whom to check Moves
	 * @param board		Board to check
	 * @return			a LinkedList<Move> of all possible moves
	 */
	private LinkedList<Move> getPossibleMoves(Board b, int player) {
		LinkedList<Move> moves = new LinkedList<Move>();
		for(int j = 0; j < board.size; j++) {
			for(int i = 0; i < board.size; i++) {
				if(b.getPiece(i,j) == Game.EMPTY) {
					moves.add(new Move(i, j));
				}
			}
		}
		return moves;
	}
}

/**
 * Data holder class that represents the coordinates of a single move.
 * @author	Kushal Ranjan
 * @version 050513
 */
class Move {
	int x, y;
	Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "Move: (" + x + ", " + y + ")";
	}
}

/**
 * Data holder class that represents a move and its associated score.
 * @author	Kushal Ranjan
 * @version 050513
 */
class Best {
	Move m;
	int score;
	Best(Move m, int score) {
		this.m = m;
		this.score = score;
	}
}
