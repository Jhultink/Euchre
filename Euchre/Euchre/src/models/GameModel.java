package models;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Class that contains necessary components to run a Euchre game instance.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class GameModel {
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
	private Suit trump;

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
	 * Holds the 4 cards played by each player or null if they haven't played
	 * yet.
	 */
	public CardsInPlay cardsInPlay;

	/**
	 * Black team overall game score.
	 */
	private int blackGameScore;

	/**
	 * Red team overall game score.
	 */
	private int redGameScore;

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
		
		trump = null;
		
		blackOne = new Player(Teams.BLACK, PlayerNumber.FIRST);
		blackTwo = new Player(Teams.BLACK, PlayerNumber.SECOND);
		redOne = new Player(Teams.RED, PlayerNumber.FIRST);
		redTwo = new Player(Teams.RED, PlayerNumber.SECOND);

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
	 * @param startingTeam
	 *            First Team to play
	 * @param startingPlayerNumber
	 *            First player to get a hand
	 */
	public void newHand(final Teams startingTeam, final PlayerNumber startingPlayerNumber) {
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

	public void setCurrentPlayer(Player currentPlayer) {
		currentTeam = currentPlayer.team;
		currentPlayerNumber = currentPlayer.playerPosition;
	}

	/**
	 * Returns the player with the position of the passed parameters.
	 * 
	 * @param team
	 *            Team of player
	 * @param number
	 *            Number of player
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
	 *            of player
	 * @param number
	 *            of player
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
	}

	/**
	 * Checks to see if a card is playable.
	 * 
	 * @param selectedCard
	 *            Card to check ability to play
	 * @return boolean value of card's ability to be played
	 */
	public boolean isValidPlay(Card selectedCard, Player player) {
		if (!getCurrentPlayer().equals(player))
			return false;
		return true;
	}

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

	public void clearTable() {
		cardsInPlay.clear();
	}

	/**
	 * Checks to see if round is over.
	 * 
	 * @return boolean value of players still having cards.
	 */
	public boolean isHandOver() {
		return blackOne.getHand().getCards().isEmpty() && blackTwo.getHand().getCards().isEmpty()
				&& redOne.getHand().getCards().isEmpty() && redTwo.getHand().getCards().isEmpty();
	}

}
