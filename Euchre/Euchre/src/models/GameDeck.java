package models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * GameDeck class used for maintaining an array list of card
 *	 classes.
 * @author Jaredt Hultink, Ryan Jones,
 *	 Keith Rodgers
 *
 */
public class GameDeck {
	
    /** ArrayList to contain instances of Cards. */
    ArrayList<Card> deck;
    
    /** Cards to initialize. */
    
	
    /**
     * Default Constructor for the class. Creates full deck and shuffles it
     */
    GameDeck() {
        this.deck = new ArrayList<Card>();
        
        // Add cards to deck
        for (Suit suit : Suit.values()) {
        	for (CardValue cardValue : CardValue.values()) {
    			deck.add(new Card(cardValue, suit));
    		}
		}
        
        shuffle();        
    }
    
    /**
     * Shuffles up the deck
     */
    public void shuffle(){
    	Collections.shuffle(this.deck);
    }
    
    
}

