package models;

/**
 * Card class contains methods and values to initialize card object.
 * 
 * @author Jaredt Hultink, Ryan Jones
 *         Keith Rodgers
 */
public class Card {

    /** Value of card. */
    private CardValue cardValue;

    /** Suit */
    private Suit cardSuit;
    
    /**
     * Constructor for the class.
     *
     * @param newCardValue
     *            program-defined card value
     * @param newCardSuit
     *            program-defined value for suit
     **/
    public Card(CardValue value, Suit suit) {

        // Initialize card value and suit.
    	this.cardValue = value;
        this.cardSuit = suit;
        
        // Set card's string value based on integer value.
        
    }

    /**
     * Method that returns the integer value of the card.
     *
     * @return cardValue
     *             Value to return.
     * @throws Exception 
     */
    public int getCardIntValue() throws Exception {
    	
    	int value;
    	
    	switch (this.cardValue) {
	        case NINE:
	        	value = 9;
	            break;
	        case TEN:
	        	value = 10;
	            break;
	        case JACK:
	        	value = 11;
	            break;
	        case QUEEN:
	        	value = 12;
	            break;
	        case KING:
	        	value = 13;
	            break;
	        case ACE:
	        	value = 14;
	            break;
	        default:
	            throw new Exception("Could not fine value " + this.cardSuit);
    	}
    	
    	return value;
    }

    /**
     * Method that returns the suit of the card.
     *
     * @return cardSuit
     *             String value to return.
     */
    public Suit getCardSuit() {
        return cardSuit;
    }

    /**
     * Method that returns a string name of the card.
     *
     * @return cardStringValue
     *             String value to return.
     * @throws Exception 
     */
    public String getCardStringValue() throws Exception {
    	String value;
    	
    	switch (this.cardValue) {
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
	            throw new Exception("Could not fine value " + this.cardValue);
    	}
    	
    	return value;
    }
}
