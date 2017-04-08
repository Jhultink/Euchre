package models;

import java.io.Serializable;

/**
 * Class that holds suit information.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public enum Suit implements Serializable {
  
  /** Clubs suit. */
  CLUBS,
  /** Spades suit. */
  SPADES,
  /** Spades suit. */
  HEARTS,
  /** Spades suit. */
  DIAMONDS;

  /**
   * Overloading toString method for Suit.
   * @return string representation of suit
   */
  public String toString() {
    if (this == Suit.CLUBS) {
      return "Clubs";
    }
    if (this == Suit.SPADES) {
      return "Spades";
    }
    if (this == Suit.HEARTS) {
      return "Hearts";
    }
    return "Diamonds";
  }
  
  /**
   * Returns the other suit of the same color.
   * @return suit other suit
   */
  public Suit oppositeSuit() {
    if (this == Suit.CLUBS) {
      return SPADES;
    }
    if (this == Suit.SPADES) {
      return CLUBS;
    }
    if (this == Suit.HEARTS) {
      return Suit.DIAMONDS;
    }
    return HEARTS;
  }
}
