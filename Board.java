/**
 * Representation of a board for a game of Tic-Tac-Toe.
 * @author	Kushal Ranjan
 * @version 050513
 */
class Board {

	Piece[][] board;
	int size;
	int numPieces; //Keeps track of the number of occupied spaces
	
	/**
	 * Constructs a new board with a set size.
	 * @param size	width of the board
	 */
	Board(int size) {
		this.size = size;
		board = new Piece[size][size];
		numPieces = 0;
	}
	
	/**
	 * Determines whether the player type has a win on the current board.
	 * @param type	player for which to check
	 * @return		true iff type has a win
	 */
	boolean hasWin(int type) {
		return hasHorizWin(type) || hasVertWin(type) || hasDiagWin(type);
	}
	
	/**
	 * Checks whether there are any empty spaces left.
	 * @return	true iff there are empty spaces left on the board
	 */
	boolean hasEmptySpaces() {
		return numPieces < size*size;
	}
	
	/**
	 * Adds a piece to the specified coordinates if the space is unoccupied.
	 * @param type	the type of piece to add according to Game
	 * @param x		x-coordinate of desired space
	 * @param y		y-coordinate of desired space
	 * @return		true iff the piece was added correctly
	 */
	boolean addPiece(int type, int x, int y) {
		if(board[y][x] != null) {
			return false;
		} else {
			board[y][x] = new Piece(type, x, y);
			numPieces++;
			return true;
		}
	}
	
	/**
	 * Returns the int representation of the piece at (x, y).
	 * @param x	x-coordinate to check
	 * @param y y-coordinate to check
	 * @return	the Game-class representation of the piece at (x, y)
	 */
	int getPiece(int x, int y) {
		if(board[y][x] != null) {
			return board[y][x].type;
		} else {
			return Game.EMPTY;
		}
	}
	
	/**
	 * Removes a piece from the board.
	 * @param x	x-coordinate to remove
	 * @param y	y-coordinate to remove
	 */
	void undoAdd(int x, int y) {
		if(board[y][x] != null) {
			board[y][x] = null;
			numPieces--;
		}
	}
	
	/**
	 * Prints a text-based representation of the board.
	 */
	public String toString() {
		String out = "";
		for(int j = 0; j < size; j++) { //Rows
			for(int i = 0; i < size; i++) {
				String type = "";
				switch(getPiece(i, j)) {
					case Game.EMPTY:	type = " ";
										break;
					case Game.CIRCLE:	type = "O";
										break;
					case Game.CROSS:	type = "X";
										break;
				}
				out += type;
				if(i != size-1){
					out += "|";
				} else {
					out += "\n"; //Next line
				}
			}
			if(j != size - 1) {
				for(int i = 0; i < 2*size - 1; i++) {
					out += "-";
				}
				out += "\n";
			}
		}
		return out;
	}
	
	/**
	 * Checks whether there is a full horizontal row of type Pieces.
	 * @param type	type to check
	 * @return		true iff there is a full horizontal row of type Pieces
	 */
	private boolean hasHorizWin(int type) {
		for(int j = 0; j < size; j++) {
			for(int i = 0; i < size; i++) {
				if(getPiece(i, j) != type) {
					break;
				} else if(i == size - 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks whether there is a full vertical column of type Pieces.
	 * @param type	type to check
	 * @return		true iff there is a full vertical column of type Pieces
	 */
	private boolean hasVertWin(int type) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(getPiece(i, j) != type) {
					break;
				} else if(j == size - 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks whether there is a full diagonal of type Pieces.
	 * @param type	type to check
	 * @return		true iff there is a full diagonal of type Pieces
	 */
	private boolean hasDiagWin(int type) {
		for(int i = 0; i < size; i++) {
			if(getPiece(i, i) != type) {
				break;
			} else if(i == size - 1) {
				return true;
			}
		}
		for(int i = 0; i < size; i++) {
			if(getPiece(i, size - 1 - i) != type) {
				break;
			} else if(i == size - 1) {
				return true;
			}
		}
		return false;
	}
}

/**
 * Data holder class that keeps track of the information for a single piece
 * @author	Kushal Ranjan
 * @version 050513
 */
class Piece {
	int type, x, y;
	Piece(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
