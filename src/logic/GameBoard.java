/**
 * Spiellogik und Spielbrett
 */
package logic;

import util.Sign;

/**
 * 
 * @author Phil Seelmann
 *
 */
public class GameBoard {

	private Sign[][] board;

	/**
	 * Konstruktor für das Spielbrett
	 */
	public GameBoard() {
		board = new Sign[3][3];
	}

	/**
	 * Plaziert das übergebene Zeichen
	 * @param sign Das Zeichen
	 * @param coord1 Vertikale Koordinate
	 * @param coord2 Horizontale Koordinate
	 * @return Erfolgreich als Boolean
	 */
	public boolean placeASign(Sign sign, int coord1, int coord2) {
		boolean result;
		if (checkIfBlocked(coord1, coord2)) {
			Sign[][] resultArray = getBoard();
			resultArray[coord1][coord2] = sign;
			this.setBoard(resultArray);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Überprüfen, ob es einen Sieger gibt
	 * @return
	 */
	public boolean checkAWinner() {
		boolean result;
		if (threeSameSigns()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Überprüfen auf drei gleiche in Reihe
	 * @return
	 */
	private boolean threeSameSigns() {
		Sign[][] resultArray = getBoard();
		boolean result;
		if ((resultArray[0][0] != null
				&& (resultArray[0][0].equals(resultArray[0][1]) && resultArray[0][0].equals(resultArray[0][2])))
				|| (resultArray[1][0] != null
						&& (resultArray[1][0].equals(resultArray[1][1]) && resultArray[1][0].equals(resultArray[1][2])))
				|| (resultArray[2][0] != null
						&& (resultArray[2][0].equals(resultArray[2][1]) && resultArray[2][0].equals(resultArray[2][2])))
				|| (resultArray[0][0] != null
						&& (resultArray[0][0].equals(resultArray[1][0]) && resultArray[0][0].equals(resultArray[2][0])))
				|| (resultArray[0][1] != null
						&& (resultArray[0][1].equals(resultArray[1][1]) && resultArray[0][1].equals(resultArray[2][1])))
				|| (resultArray[0][2] != null
						&& (resultArray[0][2].equals(resultArray[1][2]) && resultArray[0][2].equals(resultArray[2][2])))
				|| (resultArray[0][0] != null
						&& (resultArray[0][0].equals(resultArray[1][1]) && resultArray[0][0].equals(resultArray[2][2])))
				|| (resultArray[0][2] != null && (resultArray[0][2].equals(resultArray[1][1])
						&& resultArray[0][2].equals(resultArray[2][0])))) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Überprüfen auf bereits belegtes Feld
	 * @param coord1 vertikale Koordinate
	 * @param coord2 horizontale Koordinate
	 * @return geblockt als boolean
	 */
	private boolean checkIfBlocked(int coord1, int coord2) {
		boolean result;
		if (getBoard()[coord1][coord2] != null && !getBoard()[coord1][coord2].equals("")) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	/**
	 * Getter für das Spielbrett
	 * @return
	 */
	public Sign[][] getBoard() {
		return board;
	}

	/**
	 * Setter für das Spielbrett
	 * @param board
	 */
	public void setBoard(Sign[][] board) {
		this.board = board;
	}
}
