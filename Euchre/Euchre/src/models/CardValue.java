package models;
/**
 * Enum of all card values from 9 through Ace.
 */
public enum CardValue {
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
	        case ACE:
	        	value = "Ace";
	            break;
	        default:
		}
		
		return value;
	}
}
