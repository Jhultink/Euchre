package models;

public enum CardValue {
	ACE,
	KING,
	QUEEN,
	JACK,
	TEN,
	NINE;
	
	public String toString(){
		
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
		}
		
		return value;
	}
}
