package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds all the cards for one player.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class Hand implements Serializable {

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
    
    /**
     * @param suit suit to check for
     * @return boolean if hand currently contains this suit
     */
    public boolean containsSuit(final Suit suit) {
      for (Card card : cards) {
        if (card.getCardSuit() == suit) {
          return true;
        }
      }      
      return false;
    }
    
    /**
     * @param card card to add to hand.
     */
    public void add(final Card card) {
      cards.add(card);
    }
    
    /**
     * @param index to remove at
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public void removeAt(final int index) throws IndexOutOfBoundsException {
      cards.remove(index);
    }
}
