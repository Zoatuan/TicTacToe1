/**
 * Die Startklasse beinhaltet den Spielablauf
 */
package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import logic.GameBoard;
import util.Sign;

public class StartGame {

	/**
	 * Programmstart
	 * @param args
	 */
	public static void main(String[] args) {
		GameBoard gameBoard = new GameBoard();
		Scanner in = new Scanner(System.in);
		System.out.println("Starting the Game");
		System.out.println("Choose a Sign! x or o");

		String sign = chooseASign(in);
		Sign usedSign = sign.equals(Sign.CROSS.getValue()) ? Sign.CROSS : Sign.CYCLE;
		Sign enemySign = sign.equals(Sign.CROSS.getValue()) ? Sign.CYCLE : Sign.CROSS;

		System.out.println("You can now place like 00 in the left-top corner");
		System.out.println("Place your Sign!");

		while (!gameBoard.checkAWinner()) {
			playTheGame(in, usedSign, gameBoard, enemySign);
			if(gameBoard.checkAWinner()) {
				System.out.println("You won!");
				return;
			}
			placeOnRandomPosition(enemySign, gameBoard);
			printTheActualGame(gameBoard);
		}
		System.out.println("You lost!");
	}

	/**
	 * Diese Methode stellt einen Zug des Spielers dar
	 * @param in Eingabescanner
	 * @param userSign Das Spielerzeichen
	 * @param gameBoard Das Spielbrett
	 * @param enemySign Das Gegnerzeichen
	 */
	private static void playTheGame(Scanner in, Sign userSign, GameBoard gameBoard, Sign enemySign) {
		String position = null;
		while (position == null) {
			try {
				position = in.next();
				if (!position.matches("[0-2][0-2]")) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("Wrong input!");
			}
			gameBoard.placeASign(userSign, Integer.valueOf(position.split("")[0]),
					Integer.valueOf(position.split("")[1]));
		}

	}

	/**
	 * Der Zug des Gegners
	 * @param enemySign Das Gegnerzeichen
	 * @param gameBoard Das Spielbrett
	 */
	private static void placeOnRandomPosition(Sign enemySign, GameBoard gameBoard) {
		boolean placed = false;
		int abort = 0;
		while(!placed) {
			int pos1 = getRandomNumber();
			int pos2 = getRandomNumber();
			placed = gameBoard.placeASign(enemySign, pos1, pos2);
			abort++;
			if(abort > 1000) {
				System.out.println("Equal!");
				return;
			}
		}
	}
	
	/**
	 * Erzeugt für den Computer eine Randomkoordinate
	 * @return Koordinate
	 */
	private static int getRandomNumber() {
		return (int) Math.round((Math.random() * 2));
	}
	/**
	 * Abfrage des Spielers nach einem Zeichen
	 * @param in InputScanner
	 * @return Das gewählte Zeichen
	 */
	private static String chooseASign(Scanner in) {
		String i = null;
		while (i == null) {
			try {
				i = in.next();
				if (!i.equalsIgnoreCase("x") && !i.equalsIgnoreCase("o")) {
					i = null;
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("try again ...");
			}
		}
		System.out.println("You picked " + i);
		return i;
	}

	/**
	 * Ausgabe des Spielfeldes
	 * @param gameBoard Das Spielbrett
	 */
	private static void printTheActualGame(GameBoard gameBoard) {
		Sign[][] board = gameBoard.getBoard();
		System.out.println("[" + board[0][0] + "][" + board[0][1] + "][" + board[0][2] + "]");
		System.out.println("[" + board[1][0] + "][" + board[1][1] + "][" + board[1][2] + "]");
		System.out.println("[" + board[2][0] + "][" + board[2][1] + "][" + board[2][2] + "]");
	}
}
