package models;

import java.util.ArrayList;

/**
 * GameDeck class used for maintaining an array list of card
 *	 classes.
 * @author Jaredt Hultink, Ryan Jones,
 *	 Keith Rodgers
 *
 */
public class GameDeck {
	
    ArrayList<Card> deck;
	
    /**
     * Default Constructor for the class.
     */
    GameDeck() {
        deck = new ArrayList<Card>();
    }
}

