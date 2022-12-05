package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Archbishop;
import chess.pieces.Bishop;
import chess.pieces.Chancellor;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class CapablancaChessMatch extends ChessMatch{
	String setup;
	public CapablancaChessMatch(String setup) {
		super("Capablanca");
		board = new Board(8, 10);
		turn = 1;
		currentPlayer = Color.WHITE;
		this.setup = setup;
		initialSetup(setup);
	}
	@Override
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	@Override
	public ChessPiece replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("A") && !type.equals("C") && !type.equals("Q")) {
			return promoted;
		}

		Position pos = promoted.getChessPosition().toPosition();
		Piece p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);

		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);

		return newPiece;
	}
	@Override
	protected ChessPiece newPiece(String type, Color color) {
		if (type.equals("B")) return new Bishop(board, color);
		if (type.equals("N")) return new Knight(board, color);
		if (type.equals("A")) return new Archbishop(board, color);
		if (type.equals("C")) return new Chancellor(board, color);
		if (type.equals("Q")) return new Queen(board, color, this);
		return new Rook(board, color);
	}
	@Override
	protected void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new CapablancaChessPosition(column,row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	//Overload
	public void initialSetup(String setup){
		char r1,r2,b1,b2,n1,n2,a,c,q,k;
		switch(setup) {
		case "Gothic":
			r1 = 'a';r2 = 'j';b1 = 'c';b2 = 'h';
			n1 = 'b';n2 = 'i';a = 'g';c = 'e';
			q = 'd';k = 'f';
		case "Bird":
			r1 = 'a';r2 = 'j';b1 = 'c';b2 = 'h';
			n1 = 'b';n2 = 'i';a = 'g';c = 'd';
			q = 'e';k = 'f';
		case "Grotesque":
			r1 = 'a';r2 = 'j';b1 = 'b';b2 = 'i';
			n1 = 'd';n2 = 'g';a = 'h';c = 'f';
			q = 'c';k = 'e';
		default:
			//Gothic was chosen for the standard
			r1 = 'a';r2 = 'j';b1 = 'c';b2 = 'h';
			n1 = 'b';n2 = 'i';a = 'g';c = 'e';
			q = 'd';k = 'f';
		break;
		}
		//Rooks
	    placeNewPiece(r1, 1, new Rook(board,Color.WHITE));
	    placeNewPiece(r2, 1, new Rook(board,Color.WHITE));
	    placeNewPiece(r1, 8, new Rook(board,Color.BLACK));
	    placeNewPiece(r2, 8, new Rook(board,Color.BLACK));
		//Bishops
	    placeNewPiece(b1, 1, new Bishop(board,Color.WHITE));
	    placeNewPiece(b2, 1, new Bishop(board,Color.WHITE));
	    placeNewPiece(b1, 8, new Bishop(board,Color.BLACK));
	    placeNewPiece(b2, 8, new Bishop(board,Color.BLACK));
		//Knights
	    placeNewPiece(n1, 1, new Knight(board,Color.WHITE));
	    placeNewPiece(n2, 1, new Knight(board,Color.WHITE));
	    placeNewPiece(n1, 8, new Knight(board,Color.BLACK));
	    placeNewPiece(n2, 8, new Knight(board,Color.BLACK));
		//Archbishop and Chancellor
	    placeNewPiece(a, 1, new Archbishop(board,Color.WHITE));
	    placeNewPiece(c, 1, new Chancellor(board,Color.WHITE));
	    placeNewPiece(a, 8, new Archbishop(board,Color.BLACK));
	    placeNewPiece(c, 8, new Chancellor(board,Color.BLACK));
		//Queen and King
	    placeNewPiece(q, 1, new Queen(board,Color.WHITE, this));
	    placeNewPiece(k, 1, new King(board,Color.WHITE, this));
	    placeNewPiece(q, 8, new Queen(board,Color.BLACK, this));
	    placeNewPiece(k, 8, new King(board,Color.BLACK, this));
		//Pawns
		for (int i = 0; i<board.getColumns(); i++) {
			placeNewPiece((char)('a'+i), 2, new Pawn(board,Color.WHITE, this));
			placeNewPiece((char)('a'+i), 7, new Pawn(board,Color.BLACK, this));
		}
	}
}
