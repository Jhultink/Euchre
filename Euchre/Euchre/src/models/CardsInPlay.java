package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds values for all four cards in play.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class CardsInPlay implements Serializable {

  /** First red player's played card. */
  private Card redOneCard;

  /** Second red player's played card. */
  private Card redTwoCard;

  /** First black player's played card. */
  private Card blackOneCard;

  /** Seconds black player's played card. */
  private Card blackTwoCard;

  /** First played card. */
  private Card firstPlayedCard;
  
  /**
   * @return the redOneCard, null if no card is played yet
   */
  public Card getRedOneCard() {
    return redOneCard;
  }

  /**
   * @param mRedOneCard
   *          the mRedOneCard to set
   */
  public void setRedOneCard(final Card mRedOneCard) {
    if (isEmpty()) {
      firstPlayedCard = mRedOneCard;
    }
    this.redOneCard = mRedOneCard;
  }

  /**
   * @return the redTwoCard, null if no card is played yet
   */
  public Card getRedTwoCard() {
    return redTwoCard;
  }

  /**
   * @param mRedTwoCard
   *          the mRedTwoCard to set
   */
  public void setRedTwoCard(final Card mRedTwoCard) {
    if (isEmpty()) {
      firstPlayedCard = mRedTwoCard;
    }
    this.redTwoCard = mRedTwoCard;
  }

  /**
   * @return the blackOneCard, null if no card is played yet
   */
  public Card getBlackOneCard() {
    return blackOneCard;
  }

  /**
   * @param mBlackOneCard
   *          the mBlackOneCard to set
   */
  public void setBlackOneCard(final Card mBlackOneCard) {
    if (isEmpty()) {
      firstPlayedCard = mBlackOneCard;
    }
    this.blackOneCard = mBlackOneCard;
  }

  /**
   * @return the blackTwoCard, null if no card is played yet
   */
  public Card getBlackTwoCard() {
    return blackTwoCard;
  }

  /**
   * @param mBlackTwoCard
   *          the mBlackTwoCard to set
   */
  public void setBlackTwoCard(final Card mBlackTwoCard) {
    if (isEmpty()) {
      firstPlayedCard = mBlackTwoCard;
    }
    this.blackTwoCard = mBlackTwoCard;
  }

  /**
   * Assigns the card to appropriate slot.
   * 
   * @param mCard
   *          card to assign
   * @param team
   *          team of card
   * @param playerNumber
   *          playerNumber of card
   */
  public void setCard(final Card mCard, final Teams team,
      final PlayerNumber playerNumber) {

    if (team == Teams.RED) {
      if (playerNumber == PlayerNumber.FIRST) {
        setRedOneCard(mCard);
      } else {
        setRedTwoCard(mCard);
      }
    } else {
      if (playerNumber == PlayerNumber.FIRST) {
        setBlackOneCard(mCard);
      } else {
        setBlackTwoCard(mCard);
      }
    }
  }
  
  /**
   * @param team team of card to get
   * @param playerNumber playerNumber of card to get
   * @return card car passed player info
   */
  public Card getCard(final Teams team, final PlayerNumber playerNumber) {

    if (team == Teams.RED) {
      if (playerNumber == PlayerNumber.FIRST) {
        return getRedOneCard();
      } else {
        return getRedTwoCard();
      }
    } else {
      if (playerNumber == PlayerNumber.FIRST) {
        return getBlackOneCard();
      } else {
        return getBlackTwoCard();
      }
    }
  }

  /**
   * Override that takes a player.
   * @param player player who owns card
   * @return card of player
   */
  public Card getCard(final Player player) {
    return getCard(player.getTeam(), player.getPlayerPosition());
  }
  
  /**
   * Returns true if all four slots are full and false otherwise.
   * 
   * @return Boolean value of all cards played
   */
  public boolean allPlayed() {
    boolean redOneTrue = (this.getRedOneCard() != null);
    boolean redTwoTrue = (this.getRedTwoCard() != null);
    boolean blackOneTrue = (this.getBlackOneCard() != null);
    boolean blackTwoTrue = (this.getBlackTwoCard() != null);

    if (redOneTrue && redTwoTrue && blackOneTrue && blackTwoTrue) {
      return true;
    }

    return false;
  }
  /**
   * @return boolean value of all played
   */
  public boolean isEmpty() {
    return (this.getRedOneCard() == null)
            && (this.getRedTwoCard() == null)
            && (this.getBlackOneCard() == null)
            && (this.getBlackTwoCard() == null);
  }

  /**
   * Clears this object.
   */
  public void clear() {
    this.setBlackOneCard(null);
    this.setBlackTwoCard(null);
    this.setRedOneCard(null);
    this.setRedTwoCard(null);
  }
  
  /**
   * @return first played card
   */
  public Card getFirstPlayedCard() {
    return firstPlayedCard;
  }
  
  /**
   * Gets all cards in play.
   * @return all cards in play
   */
  public ArrayList<Card> getAllCards() {
    ArrayList<Card> cards = new ArrayList<Card>();
    cards.add(getBlackOneCard());
    cards.add(getBlackTwoCard());
    cards.add(getRedOneCard());
    cards.add(getRedTwoCard());
    return cards;
  }
  
  /**
   * @param card card to see if it belongs to a player
   * @return Team who card belongs to
   */
  public Teams getTeamOf(final Card card) {
    if (card.equals(getBlackOneCard()) || card.equals(getBlackTwoCard())) {
      return Teams.BLACK;
    } else if (card.equals(getRedOneCard()) || card.equals(getRedTwoCard())) {
      return Teams.RED;
    } else {
      return null;
    }    
  }
  /**
   * 
   * @param card card to see if it belongs to a player
   * @return PlayerNumber who card belongs to
   */
  public PlayerNumber getPlayerNumberOf(final Card card) {
    if (card.equals(getRedOneCard()) || card.equals(getBlackOneCard())) {
      return PlayerNumber.FIRST;
    } else if (card.equals(getRedTwoCard()) || card.equals(getBlackTwoCard())) {
      return PlayerNumber.SECOND;
    } else {
      return null;
    }
  }
}
