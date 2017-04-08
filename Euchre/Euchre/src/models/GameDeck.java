package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * GameDeck class used for maintaining an array list of card classes.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class GameDeck implements Serializable {

    /** ArrayList to contain instances of Cards. */
    private ArrayList<Card> deck;

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
     * Shuffles the deck.
     */
    public void shuffle() {
    	Collections.shuffle(this.deck);
    }
    
    /**
     * Deals out 5 cards to each player.
     * @return deal - Newly dealt hands
     */
    public Deal deal() {

    	Deal deal = new Deal();
    
    	// Add 5 cards to each hand
    	deal.setRedOne(new Hand(new ArrayList<Card>(deck.subList(0, 5))));
    	deal.setRedTwo(new Hand(new ArrayList<Card>(deck.subList(5, 10))));
    	deal.setBlackOne(new Hand(new ArrayList<Card>(deck.subList(10, 15))));
    	deal.setBlackTwo(new Hand(new ArrayList<Card>(deck.subList(15, 20))));
    
    	//deal.setHiddenCards(deck.subList(20, 23).toArray(new Card[3]));
    	deal.setTrump(deck.get(23));
    
    	return deal;
    }
}
