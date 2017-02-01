package models;

import java.util.ArrayList;

/**
 * This class holds all the cards for one player
 * @author OWNER
 *
 */
public class Hand {

	/**
	 * Player cards
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Creates new Hand with passed cards
	 * @param cards
	 */
	public Hand(ArrayList<Card> cards) {
		this.cards = cards; 
	}
	
	/**
	 * Returns cards in this hand
	 * @return cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
}
