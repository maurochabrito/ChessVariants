package boardgame;

public class Board {
	private int rows;
	private int collumns;
	private Piece[][] pieces;
	
	public Board(int rows, int collumns) {
		this.rows = rows;
		this.collumns = collumns;
		pieces = new Piece[rows][collumns];
	}
	
	
}
