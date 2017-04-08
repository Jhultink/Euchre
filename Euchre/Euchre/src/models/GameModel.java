package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that contains necessary components to run a Euchre game instance.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class GameModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Object representing the team of the player whose turn it is.
   */
  private Teams startingTeam;

  /**
   * Object representing the player number of the player whose turn it is.
   */
  private PlayerNumber startingPlayerNumber;

  /**
   * Object representing the team of the player whose turn it is.
   */
  private Teams currentTeam;

  /**
   * Object representing the player number of the player whose turn it is.
   */
  private PlayerNumber currentPlayerNumber;

  /**
   * Suit of current trump.
   */
  private Suit trumpSuit;

  /**
   * initial trump card.
   */
  private Card trumpCard;

  /**
   * Black team, player one.
   */
  private Player blackOne;

  /**
   * Black team, player two.
   */
  private Player blackTwo;

  /**
   * Red team, player one.
   */
  private Player redOne;

  /**
   * Red team, player two.
   */
  private Player redTwo;

  /**
   * Deck of cards.
   */
  private GameDeck deck;

  /**
   * Holds the 4 cards played by each player or null if they haven't played yet.
   */
  private CardsInPlay cardsInPlay;

  /**
   * @return the cardsInPlay
   */
  public CardsInPlay getCardsInPlay() {
    return cardsInPlay;
  }

  /**
   * Black team overall game score.
   */
  private int blackGameScore;

  /**
   * Red team overall game score.
   */
  private int redGameScore;

  /**
   * Team that called trump.
   */
  private Teams teamWhoCalledTrump;

  /**
   * Black team current hand score.
   */
  private int blackHandScore;

  /**
   * Red team current hand score.
   */
  private int redHandScore;

  /**
   * Initializes the four players.
   */
  public GameModel() {

    blackOne = new Player(Teams.BLACK, PlayerNumber.FIRST);
    blackTwo = new Player(Teams.BLACK, PlayerNumber.SECOND);
    redOne = new Player(Teams.RED, PlayerNumber.FIRST);
    redTwo = new Player(Teams.RED, PlayerNumber.SECOND);

    blackOne.setIsAI(false);
    blackTwo.setIsAI(true);
    redOne.setIsAI(true);
    redTwo.setIsAI(true);
    
    deck = new GameDeck();
    cardsInPlay = new CardsInPlay();

    blackGameScore = 0;
    redGameScore = 0;
    blackHandScore = 0;
    redHandScore = 0;
  }

  /**
   * Sets up model to have a completely new hand.
   * 
   * @param pStartingTeam
   *          First Team to play
   * @param pStartingPlayerNumber
   *          First player to get a hand
   */
  public void newHand(final Teams pStartingTeam,
      final PlayerNumber pStartingPlayerNumber) {
    
    this.startingTeam = pStartingTeam;
    this.startingPlayerNumber = pStartingPlayerNumber;

    this.currentTeam = pStartingTeam;
    this.currentPlayerNumber = pStartingPlayerNumber;

    this.clearTable();

    deck.shuffle();
    dealOutCards();

    // reset current hand score
    blackHandScore = 0;
    redHandScore = 0;
  }

  /**
   * Returns player who's turn it currently is.
   * 
   * @return current player
   */
  public Player getCurrentPlayer() {
    return getPlayer(currentTeam, currentPlayerNumber);
  }

  /**
   * Sets the current player.
   * 
   * @param currentPlayer
   *          player to set as current player
   */
  public void setCurrentPlayer(final Player currentPlayer) {
    setCurrentTeam(currentPlayer.getTeam());
    setCurrentPlayerNumber(currentPlayer.getPlayerPosition());
  }

  /**
   * Returns the player with the position of the passed parameters.
   * 
   * @param team
   *          Team of player
   * @param number
   *          Number of player
   * @return player objects
   */
  public Player getPlayer(final Teams team, final PlayerNumber number) {

    if (team == Teams.BLACK) {
      if (number == PlayerNumber.FIRST) {
        return blackOne;
      } else {
        return blackTwo;
      }
    } else {
      if (number == PlayerNumber.FIRST) {
        return redOne;
      } else {
        return redTwo;
      }
    }
  }

  /**
   * Gets hand of passed player info.
   * 
   * @param team
   *          of player
   * @param number
   *          of player
   * @return Hand
   */
  public Hand getHandOf(final Teams team, final PlayerNumber number) {
    return getPlayer(team, number).getHand();
  }

  /**
   * Deals out cards to players.
   */
  private void dealOutCards() {
    Deal deal = deck.deal();

    redOne.setHand(deal.getRedOne());
    redTwo.setHand(deal.getRedTwo());
    blackOne.setHand(deal.getBlackOne());
    blackTwo.setHand(deal.getBlackTwo());

    trumpCard = deal.getTrump();
    trumpSuit = trumpCard.getCardSuit();
  }

  /**
   * Checks to see if a card is playable.
   * 
   * @param selectedCard
   *          Card to check ability to play
   * @param player
   *          current player
   * @return boolean value of card's ability to be played
   */
  public boolean isValidPlay(final Card selectedCard, final Player player) {

    if (!getCurrentPlayer().equals(player)) {
      return false;
    }
    
    
    // If no cards are played, any play is valid
    if (getCardsInPlay().isEmpty()) {
      return true;
    }

    Suit leadSuit = getFirstPlayedCard().getCardSuit();

    // If player does not have the lead suit, any play is valid
    if (!player.getHand().containsSuit(leadSuit)) {
      return true;
    } else {
      // Play valid only if played card suit matches lead suit
      return selectedCard.getCardSuit() == leadSuit;
    }
  }

  /**
   * @return all card in players hands, not including those that are currently
   *         in play
   */
  public ArrayList<Card> getAllCards() {
    ArrayList<Card> cards = getHandOf(Teams.RED, PlayerNumber.FIRST).getCards();
    cards.addAll(getHandOf(Teams.RED, PlayerNumber.SECOND).getCards());
    cards.addAll(getHandOf(Teams.BLACK, PlayerNumber.FIRST).getCards());
    cards.addAll(getHandOf(Teams.BLACK, PlayerNumber.SECOND).getCards());

    return cards;
  }

  /**
   * @return the currentTeam
   */
  public Teams getCurrentTeam() {
    return currentTeam;
  }

  /**
   * Returns the current player.
   * 
   * @return the currentPlayerNumber
   */
  public PlayerNumber getCurrentPlayerNumber() {
    return currentPlayerNumber;
  }

  /**
   * @param pCurrentTeam
   *          the currentTeam to set
   */
  public void setCurrentTeam(final Teams pCurrentTeam) {
    this.currentTeam = pCurrentTeam;
  }

  /**
   * @param pCurrentPlayerNumber
   *          the currentPlayerNumber to set
   */
  public void setCurrentPlayerNumber(final PlayerNumber pCurrentPlayerNumber) {
    this.currentPlayerNumber = pCurrentPlayerNumber;
  }

  /**
   * Clears table.
   */
  public void clearTable() {
    cardsInPlay.clear();
  }

  /**
   * Checks to see if round is over.
   * 
   * @return boolean value of players still having cards.
   */
  public boolean isHandOver() {
    if (!blackOne.getHand().getCards().isEmpty()) {
      return false;
    }
    if (!blackTwo.getHand().getCards().isEmpty()) {
      return false;
    }
    if (!redOne.getHand().getCards().isEmpty()) {
      return false;
    }
    if (!redTwo.getHand().getCards().isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * @return current trump card
   */
  public Card getTrumpCard() {
    return trumpCard;
  }

  /**
   * @return current trump suit
   */
  public Suit getTrumpSuit() {
    return trumpSuit;
  }

  /**
   * Sets the next player as the current player.
   * 
   * @return the current player after switching
   */
  public Player nextPlayer() {

    if (currentPlayerNumber == PlayerNumber.FIRST && currentTeam == Teams.RED) {

      currentTeam = Teams.BLACK;
      currentPlayerNumber = PlayerNumber.FIRST;
    } else if (currentPlayerNumber == PlayerNumber.FIRST
        && currentTeam == Teams.BLACK) {

      currentTeam = Teams.RED;
      currentPlayerNumber = PlayerNumber.SECOND;
    } else if (currentPlayerNumber == PlayerNumber.SECOND
        && currentTeam == Teams.RED) {

      currentTeam = Teams.BLACK;
      currentPlayerNumber = PlayerNumber.SECOND;
    }  else if (currentPlayerNumber == PlayerNumber.SECOND
        && currentTeam == Teams.BLACK) {

      currentPlayerNumber = PlayerNumber.FIRST;
      currentTeam = Teams.RED;
    }

    return getCurrentPlayer();
  }
  
  
  /**
   * Sets the next player as the current player.
   * 
   * @return the current player after switching
   */
  public Player getNextPlayer() {

    Teams tempTeam = null;
    PlayerNumber tempPlayerNumber = null;
    
    if (currentPlayerNumber == PlayerNumber.FIRST && currentTeam == Teams.RED) {

      tempTeam = Teams.BLACK;
      tempPlayerNumber = PlayerNumber.FIRST;
    } else if (currentPlayerNumber == PlayerNumber.FIRST
        && currentTeam == Teams.BLACK) {

      tempTeam = Teams.RED;
      tempPlayerNumber = PlayerNumber.SECOND;
    } else if (currentPlayerNumber == PlayerNumber.SECOND
        && currentTeam == Teams.RED) {

      tempTeam = Teams.BLACK;
      tempPlayerNumber = PlayerNumber.SECOND;
    }  else if (currentPlayerNumber == PlayerNumber.SECOND
        && currentTeam == Teams.BLACK) {

      tempPlayerNumber = PlayerNumber.FIRST;
      tempTeam = Teams.RED;
    }

    return getPlayer(tempTeam, tempPlayerNumber);
  }

  /**
   * @param pTrumpSuit
   *          suit to set as trump
   */
  public void setTrumpSuit(final Suit pTrumpSuit) {
    this.trumpSuit = pTrumpSuit;
  }

  /**
   * @return the startingTeam
   */
  public Teams getStartingTeam() {
    return startingTeam;
  }

  /**
   * @param pStartingTeam
   *          the startingTeam to set
   */
  public void setStartingTeam(final Teams pStartingTeam) {
    this.startingTeam = pStartingTeam;
  }

  /**
   * @return the startingPlayerNumber
   */
  public PlayerNumber getStartingPlayerNumber() {
    return startingPlayerNumber;
  }

  /**
   * @param pStartingPlayerNumber
   *          the startingPlayerNumber to set
   */
  public void setStartingPlayerNumber(
      final PlayerNumber pStartingPlayerNumber) {
    this.startingPlayerNumber = pStartingPlayerNumber;
  }

  /**
   * @return first played card
   */
  public Card getFirstPlayedCard() {
    //return cardsInPlay.getCard(startingTeam, startingPlayerNumber);
    return cardsInPlay.getFirstPlayedCard();
  }

  /**
   * @return the teamWhoCalledTrump
   */
  public Teams getTeamWhoCalledTrump() {
    return teamWhoCalledTrump;
  }

  /**
   * @param pTeamWhoCalledTrump
   *          the teamWhoCalledTrump to set
   */
  public void setTeamWhoCalledTrump(final Teams pTeamWhoCalledTrump) {
    this.teamWhoCalledTrump = pTeamWhoCalledTrump;
  }

  /**
   * Increase black hand score by one.
   */
  public void increaseBlackHandScore() {
    blackHandScore++;
  }

  /**
   * Increase red hand score by one.
   */
  public void increaseRedHandScore() {
    redHandScore++;
  }

  /**
   * Increases black game score.
   * 
   * @param i
   *          increment
   */
  public void addToBlackScore(final int i) {
    blackGameScore += i;
  }

  /**
   * Increases red game score.
   * 
   * @param i
   *          increment
   */
  public void addToRedScore(final int i) {
    redGameScore += i;
  }
  
  /**
   * @return black game score
   */
  public int getBlackGameScore() {
    return blackGameScore;
  }
  
  /**
   * @return red game score
   */
  public int getRedGameScore() {
    return redGameScore;
  }
  
  /**
   * @return black hand score
   */
  public int getBlackHandScore() {
    return blackHandScore;
  }
  
  /**
   * @return red hand score
   */
  public int getRedHandScore() {
    return redHandScore;
  }

  /**
   * @return array of players in order
   */
  public Player[] getPlayersInOrder() {
    Player[] players = new Player[4];
    players[0] = getCurrentPlayer();
    players[1] = nextPlayer();
    players[2] = nextPlayer();
    players[3] = nextPlayer();
    
    nextPlayer();
    
    return players;
  }
  
  /**
   * @return bool whether trick is over
   */
  public boolean isTrickOver() {
    return cardsInPlay.allPlayed();
  }
}
