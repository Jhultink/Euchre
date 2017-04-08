package models;

import java.io.Serializable;

/**
 * Card class contains methods and values to initialize card object.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 * 
 */
public class Card implements Comparable<Card>, Serializable {

  /** Value of card. */
  private CardValue cardValue;

  /** Suit. */
  private Suit cardSuit;

  /**
   * Constructor for the class.
   *
   * @param value
   *          program-defined card value
   * @param suit
   *          program-defined value for suit
   **/
  public Card(final CardValue value, final Suit suit) {

    // Initialize card value and suit.
    this.cardValue = value;
    this.cardSuit = suit;

    // Set card's string value based on integer value.

  }

  /**
   * Method that returns the integer value of the card.
   *
   * @return cardValue Value to return.
   */
  public int getCardIntValue() {
    
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
    default:
      value = 14;
    }

    return value;
  }

  /**
   * Getter for CardValue.
   * 
   * @return cardValue
   */
  public CardValue getCardValue() {
    return cardValue;
  }

  /**
   * Method that returns the suit of the card.
   *
   * @return cardSuit String value to return.
   */
  public Suit getCardSuit() {
    return cardSuit;
  }

  /**
   * Method that returns a string name of the card.
   *
   * @return cardStringValue String value to return.
   * @throws Exception
   */
  public String getCardStringValue() {
    return getCardValue().toString() + " of " + getCardSuit().toString();
  }
  /**
   * Determines if card is trump based on passed trump.
   * Handles Jack logic
   * @param trumpSuit suit
   * @return bool is trump
   */
  public boolean isTrump(final Suit trumpSuit) {
    if (this.cardSuit == trumpSuit) {
      return true;
    }

    if (this.cardValue == CardValue.JACK) {
      if ((trumpSuit == Suit.SPADES && cardSuit == Suit.CLUBS)
          || (trumpSuit == Suit.CLUBS && cardSuit == Suit.SPADES)
          || (trumpSuit == Suit.HEARTS && cardSuit == Suit.DIAMONDS)
          || (trumpSuit == Suit.DIAMONDS && cardSuit == Suit.HEARTS)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Compares to cards based on their int values.
   * @param card Card to compare
   */
  @Override
  public int compareTo(final Card card) {
    
    return card.getCardIntValue() - this.getCardIntValue();
    
  }
  
  /**
   * Return whether or not the passed object is equal.
   * @param o object
   * @return boolean equality of objects
   */
  @Override
  public boolean equals(final Object o) {
     if (!(o instanceof Card)) {
       return false;
     } else {
       Card card = (Card) o;
       return this.getCardSuit() == card.getCardSuit() 
           && this.getCardValue() == card.getCardValue();
     }
     
     
  }
  
  /**
   * Return hash.
   * @return int hash
   */
  @Override
  public int hashCode() {
    return cardSuit.hashCode() * getCardIntValue();
  }
  
}
