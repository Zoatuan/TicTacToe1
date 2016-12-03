/**
 * Die beiden Zeichen als Enum
 */
package util;

/**
 * 
 * @author Phil Seelmann
 *
 */
public enum Sign {

	CROSS("x"), CYCLE("o");

	String value;

	/**
	 * Konstruktor
	 * @param value Beschreibung des Zeichens
	 */
	private Sign(String value) {
		this.value = value;
	}

	/**
	 * Getter des Values
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * To-String Methode
	 */
	@Override
	public String toString() {
		String result;
		result = value;
		return result;
	}
}
