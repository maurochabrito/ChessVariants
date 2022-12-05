package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private Board board;
	private int turn;
	private Color currentPlayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

	public CapablancaChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
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
}
