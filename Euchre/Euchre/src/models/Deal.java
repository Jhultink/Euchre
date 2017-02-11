package models;

import java.lang.reflect.Array;

/**
 * This class holds the result of a deal, which is returned by the
 * GameDeck.Deal() method
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class Deal {

    private Hand redOne;
    private Hand redTwo;
    private Hand blackOne;
    private Hand blackTwo;

    Card[] hiddenCards = new Card[3];

    Card trump;

    Deal() {

    }

    /**
     * 
     * @return the redOne
     */
    public Hand getRedOne() {
	return redOne;
    }

    /**
     * 
     * @param redOne
     *            the redOne to set
     */
    public void setRedOne(Hand redOne) {
	this.redOne = redOne;
    }

    /**
     * @return the redTwo
     */
    public Hand getRedTwo() {
	return redTwo;
    }

    /**
     * @param redTwo
     *            the redTwo to set
     */
    public void setRedTwo(Hand redTwo) {
	this.redTwo = redTwo;
    }

    /**
     * @return the blackOne
     */
    public Hand getBlackOne() {
	return blackOne;
    }

    /**
     * @param blackOne
     *            the blackOne to set
     */
    public void setBlackOne(Hand blackOne) {
	this.blackOne = blackOne;
    }

    /**
     * @return the blackTwo
     */
    public Hand getBlackTwo() {
	return blackTwo;
    }

    /**
     * @param blackTwo
     *            the blackTwo to set
     */
    public void setBlackTwo(Hand blackTwo) {
	this.blackTwo = blackTwo;
    }

    /**
     * @return the hiddenCards
     */
    public Card[] getHiddenCards() {
	return hiddenCards;
    }

    /**
     * @param hiddenCards
     *            the hiddenCards to set
     */
    public void setHiddenCards(Card[] hiddenCards) {
	this.hiddenCards = hiddenCards;
    }

    /**
     * @return the trump
     */
    public Card getTrump() {
	return trump;
    }

    /**
     * @param trump
     *            the trump to set
     */
    public void setTrump(Card trump) {
	this.trump = trump;
    }

}
