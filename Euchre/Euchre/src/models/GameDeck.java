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
    
    public void getHand(int hand){
    	
    }
    
    public Deal deal(){
    	
    	Deal deal = new Deal();
    	
    	// Add 5 cards to each hand
    	deal.setRedOne(new Hand(new ArrayList<Card>(deck.subList(0, 5))));
    	deal.setRedTwo(new Hand(new ArrayList<Card>(deck.subList(5, 10))));
    	deal.setBlackOne(new Hand(new ArrayList<Card>(deck.subList(10, 15))));
    	deal.setBlackTwo(new Hand(new ArrayList<Card>(deck.subList(15, 20))));
    	
    	deal.setHiddenCards(deck.subList(20, 23).toArray(new Card[3]));
    	deal.setTrump(deck.get(23));
    	
    	return deal;
    	
    }
    
    
}

