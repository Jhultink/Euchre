package models;

import java.io.Serializable;

/**
 * This class holds the result of a deal, which is returned by the
 * GameDeck.Deal() method.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class Deal implements Serializable {
  /** First red player hand. */
  private Hand redOne;

  /** Second red player hand. */
  private Hand redTwo;

  /** First black player hand. */
  private Hand blackOne;

  /** Second black player hand. */
  private Hand blackTwo;

  /** Trump card. */
  private Card trump;

  /**
   * 
   * @return the redOne
   */
  public Hand getRedOne() {
    return redOne;
  }

  /**
   * 
   * @param pRedOne
   *          the redOne to set
   */
  public void setRedOne(final Hand pRedOne) {
    this.redOne = pRedOne;
  }

  /**
   * @return the redTwo
   */
  public Hand getRedTwo() {
    return redTwo;
  }

  /**
   * @param pRredTwo
   *          the redTwo to set
   */
  public void setRedTwo(final Hand pRredTwo) {
    this.redTwo = pRredTwo;
  }

  /**
   * @return the blackOne
   */
  public Hand getBlackOne() {
    return blackOne;
  }

  /**
   * @param pblackOne
   *          the blackOne to set
   */
  public void setBlackOne(final Hand pblackOne) {
    this.blackOne = pblackOne;
  }

  /**
   * @return the blackTwo
   */
  public Hand getBlackTwo() {
    return blackTwo;
  }

  /**
   * @param pblackTwo
   *          the blackTwo to set
   */
  public void setBlackTwo(final Hand pblackTwo) {
    this.blackTwo = pblackTwo;
  }

  /**
   * @return the trump
   */
  public Card getTrump() {
    return trump;
  }

  /**
   * @param pTrump
   *          the trump to set
   */
  public void setTrump(final Card pTrump) {
    this.trump = pTrump;
  }

}
