package models;

public enum Suit {
	CLUBS,
	SPADES,
	HEARTS,
	DIAMONDS;
	
	public String toString(){
		if(this == Suit.CLUBS) return "Clubs";
		if(this == Suit.SPADES) return "Spades";
		if(this == Suit.HEARTS) return "Hearts";
		return "Diamonds";
	}
}
