/**
 * Playable, text-based version of the game Tic-Tac-Toe. Game can use any board size >= 1 and
 * any combination of human, machine, and random players.
 * @author	Kushal Ranjan
 * @version 050513
 */
public class Game {
	public static final int EMPTY = 0;
	public static final int CIRCLE = 1;
	public static final int CROSS = 2;
	
	/**
	 * Runs a single game of Tic-Tac-Toe until a player wins or no more moves can be made.
	 * @param args	args[0] is the type of the first player (human, random, or machine*).
	 * 				args[1] is the type of the second player (human, random, or machine*).
	 * 				args[2] is an int representing the side length of the board.
	 * 				*to be implemented
	 */
	public static void main(String[] args) {
		if(args.length == 0) { //If no arguments are given, runs "human machine 3"
			args = new String[3];
			args[0] = "human";
			args[1] = "machine";
			args[2] = "3";
		} else if(args.length != 3) {
			System.out.println("Invalid number of arguments. Should be <player1> <player2> <size>.");
			System.exit(0);
		}
		Board board = null;
		try {
			board = new Board(Integer.parseInt(args[2]));
		} catch(NumberFormatException e) {
			System.err.println("Board size is not an integer.");
		}
		Player playerA = createPlayer(args[0], board, Game.CIRCLE);
		Player playerB = createPlayer(args[1], board, Game.CROSS);
		System.out.println(playerA + " vs. " + playerB);
		Player currentPlayer = playerA;
		boolean win = false; //Keeps track of whether or not there is a win
		System.out.println(board);
		while(!win && board.hasEmptySpaces()) {
			currentPlayer.makeMove();
			if(board.hasWin(currentPlayer.type)) {
				win = true;
			} else if(currentPlayer == playerA) { //No win yet; switch players
				currentPlayer = playerB;
			} else {
				currentPlayer = playerA;
			}
			System.out.println(board);
		}
		if(win) {
			System.out.println(currentPlayer + " wins!");
		} else { //Board ran out of spaces
			System.out.println("Draw.");
		}
	}
	
	/**
	 * Helper method; takes in a string representing a type of player and returns a player
	 * of that type
	 * @param kind	"human", "machine"*, or "random"
	 * @param board	Board object of this Game
	 * @param type	piece type of the player to be created
	 * @return		a player of the type specified that plays with the given piece (circle or
	 * 				cross) on the given board.
	 */
	private static Player createPlayer(String kind, Board board, int type) {
		if(kind.equals("human")){
			return new HumanPlayer(board, type);
		} else if(kind.equals("random")) {
			return new RandomPlayer(board, type);
		} else if(kind.equals("machine")) {
			return new MachinePlayer(board, type);
		} else {
			System.out.println("Player type not recognized: " + kind);
			System.exit(0);
			return null;
		}
	}
	
	/**
	 * Returns the opponent of the specified player.
	 * @return	the int representation of the player in question
	 */
	static int otherPlayer(int player) {
		if(player == Game.CIRCLE) {
			return Game.CROSS;
		} else {
			return Game.CIRCLE;
		}
	}
}
