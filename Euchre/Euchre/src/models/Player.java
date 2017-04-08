package models;

import java.io.Serializable;

/**
 * Class that holds the player information.
 *
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class Player implements Serializable {

  /**
   * Position of player.
   */
  private PlayerNumber playerPosition;
  /**
   * Team player is on.
   */
  private Teams team;
  /**
   * Hand of player.
   */
  private Hand hand;
  
  /**
   * Whether player is AI.
   */
  private boolean isAI;

  /**
   * Constructor for Player class initializes player's team and position.
   * 
   * @param pTeam
   *          team of player
   * @param pPlayerPosition
   *          position of player
   */
  public Player(final Teams pTeam, final PlayerNumber pPlayerPosition) {
    this.team = pTeam;
    this.playerPosition = pPlayerPosition;
  }
  /**
   * @return hand of player
   */
  public Hand getHand() {
    return hand;
  }

  /**
   * Sets hand for player.
   * @param pHand hand
   */
  public void setHand(final Hand pHand) {
    this.hand = pHand;
  }

  /**
   * @return string representing player
   */
  @Override
  public String toString() {
    return team + " " + playerPosition;
  }

  /**
   * Determines if two players are equal.
   * Only compares team and number, not hand.
   * @return isEqual
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj == null || !(obj instanceof Player)) {
      return false;
    }

    Player player = (Player) obj;

    return this.team == player.team
        && this.playerPosition == player.playerPosition;
  }
  /**
   * Custom hashcode method.
   * @return int hashcode
   */
  @Override
  public int hashCode() {     
      return team.hashCode() 
          * playerPosition.hashCode() 
          * hand.getCards().hashCode();
  }
  /**
   * @return the playerPosition
   */
  public PlayerNumber getPlayerPosition() {
    return playerPosition;
  }
  /**
   * @param pPlayerPosition the playerPosition to set
   */
  public void setPlayerPosition(final PlayerNumber pPlayerPosition) {
    this.playerPosition = pPlayerPosition;
  }
  /**
   * @return the team
   */
  public Teams getTeam() {
    return team;
  }
  /**
   * @param pTeam the team to set
   */
  public void setTeam(final Teams pTeam) {
    this.team = pTeam;
  }
  
  /**
   * @return bool if player is AI
   */
  public boolean isAI() {
    return isAI;
  }
  
  /**
   * @param pIsAI bool isAI.
   */
  public void setIsAI(final boolean pIsAI) {
    this.isAI = pIsAI;
  }


}
