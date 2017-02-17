package models;

import java.util.ArrayList;

/**
 * This class holds all the cards for one player.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class Hand {

    /**
     * Player cards.
     */
    private ArrayList<Card> cards;

    /**
     * Creates new Hand with passed cards.
     * 
     * @param pCards cards of this hand
     */
    public Hand(final ArrayList<Card> pCards) {
    	this.cards = pCards;
    }

    /**
     * Returns cards in this hand.
     * 
     * @return cards
     */
    public ArrayList<Card> getCards() {
    	return cards;
    }
    /**
     * @param newCards sets contents of hands to cards
     */
    public void setCards(final ArrayList<Card> newCards) {
    	this.cards = newCards;
    }
    
    
    /**
     * @return if Hand is empty
     */
    public boolean isEmpty() {
    	return cards.isEmpty();
    }
}
