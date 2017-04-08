package models;

import java.io.Serializable;

/**
 * Enum of all card values from 9 through Ace.
 * 
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public enum CardValue implements Serializable {
	/** Ace. */
	ACE,
	/** King. */
	KING,
	/** Queen. */
	QUEEN,
	/** Jack. */
	JACK,
	/** Ten. */
	TEN,
	/** Nine. */
	NINE;

	/**
	 * Returns the enum to a display-friendly string.
	 * @return string
	 */
	public String toString() {
		
		String value = "invalid value";
		switch (this) {
	        case NINE:
	        	value = "9";
	            break;
	        case TEN:
	        	value = "10";
	            break;
	        case JACK:
	        	value = "Jack";
	            break;
	        case QUEEN:
	        	value = "Queen";
	            break;
	        case KING:
	        	value = "King";
	            break;
	         default:
	        	value = "Ace";
	            break;

		}
		
		return value;
	}
}
