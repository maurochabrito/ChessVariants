package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		Position pos = new Position(5,5);
		System.out.println(pos);
		//
		Board board = new Board(8,8);
		//
		System.out.println();
		ChessMatch chessmatch = new ChessMatch();
		UI.printBoard(chessmatch.getPieces());
	}

}
