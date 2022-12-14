package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.CapablancaChessMatch;
import chess.CapablancaChessPosition;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		UI.clearScreen();
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a Chess Variant to play (type the number):");
		System.out.printf("%nAvaliable:%n(1) Capablanca's Chess%n%n>> ");
		String variant = sc.nextLine();
		UI.clearScreen();
		switch(variant){
			case "1":
				System.out.println("Choose a Capablanca Chess initial setup (type the number):");
				System.out.printf("%nAvaliable:%n(1) Gothic%n(2) Bird%n(3) Grotesque%n%n>> ");
				String initialSetup = sc.nextLine();
				UI.clearScreen();
				CapablancaChessMatch chessMatch = new CapablancaChessMatch(initialSetup);
				List<ChessPiece> captured = new ArrayList<>();

				while (!chessMatch.getCheckMate()) {
					try {
						UI.clearScreen();
						UI.printMatch(chessMatch, captured);
						System.out.println();
						System.out.print("Source: ");
						CapablancaChessPosition source = UI.readCapablancaChessPosition(sc);
						boolean[][] possibleMoves = chessMatch.possibleMoves(source);
						UI.clearScreen();
						UI.printBoard(chessMatch.getPieces(), possibleMoves);
						System.out.println();
						System.out.print("Target: ");
						CapablancaChessPosition target = UI.readCapablancaChessPosition(sc);
						ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
						if (capturedPiece != null) {
							captured.add(capturedPiece);
						}
						if (chessMatch.getPromoted() != null) {
							System.out.print("Enter piece for promotion (B/N/R/A/C/Q): ");
							String type = sc.nextLine().toUpperCase();
							while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("A") && !type.equals("C") && !type.equals("Q")) {
								System.out.print("Invalid value! Enter piece for promotion (B/N/R/A/C/Q): ");
								type = sc.nextLine().toUpperCase();
							}
							chessMatch.replacePromotedPiece(type);
						}
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
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
		    break;
		    default:
		    break;
		}
	}

}
