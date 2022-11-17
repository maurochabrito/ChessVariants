package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class ProgramTesting {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				boolean[][] matrix = (chessMatch.getPieces()[7][0].possibleMoves());
				for (int i = 0; i<8; i++) {
					for (int j = 0; j<8; j++) {
						System.out.print(matrix[i][j]);
					}
					System.out.println();
				}
				System.out.println(chessMatch.getPieces()[7][0].possibleMove(new Position(7,4)));
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
	}

}
