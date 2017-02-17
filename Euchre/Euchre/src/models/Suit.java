package models;

/**
 * Class that holds suit information.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public enum Suit {
  
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
}
