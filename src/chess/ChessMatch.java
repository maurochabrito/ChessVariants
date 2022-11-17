package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.*;

public class ChessMatch {
	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece Piece) {
		board.placePiece(Piece, new ChessPosition(column,row).toPosition());
	}
	
	public void initialSetup(){
		//Rooks
		placeNewPiece('a', 1, new Rook(board,Color.WHITE));
		placeNewPiece('h', 1, new Rook(board,Color.WHITE));
		placeNewPiece('a', 8, new Rook(board,Color.BLACK));
		placeNewPiece('h', 8, new Rook(board,Color.BLACK));
		//Knights
		placeNewPiece('b', 1, new Knight(board,Color.WHITE));
		placeNewPiece('g', 1, new Knight(board,Color.WHITE));
		placeNewPiece('b', 8, new Knight(board,Color.BLACK));
		placeNewPiece('g', 8, new Knight(board,Color.BLACK));
		//Bishops
		placeNewPiece('c', 1, new Bishop(board,Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board,Color.WHITE));
		placeNewPiece('c', 8, new Bishop(board,Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board,Color.BLACK));
		//Queens
		placeNewPiece('d', 1, new Queen(board,Color.WHITE));
		placeNewPiece('d', 8, new Queen(board,Color.BLACK));
		//Kings
		placeNewPiece('e', 1, new King(board,Color.WHITE));
		placeNewPiece('e', 8, new King(board,Color.BLACK));
		//Pawns
		for (int i = 0; i<board.getColumns(); i++) {
			placeNewPiece((char)('a'+i), 2, new Pawn(board,Color.WHITE));
			placeNewPiece((char)('a'+i), 7, new Pawn(board,Color.BLACK));
		}
	}
}
