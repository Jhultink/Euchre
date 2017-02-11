package models;

/**
 * Class that contains necessary components to run a Euchre game instance.
 * 
 * @author OWNER
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

	blackOne = new Player(Teams.BLACK, PlayerNumber.FIRST);
	blackTwo = new Player(Teams.BLACK, PlayerNumber.SECOND);
	redOne = new Player(Teams.RED, PlayerNumber.FIRST);
	redTwo = new Player(Teams.RED, PlayerNumber.SECOND);

	deck = new GameDeck();

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
    public void newHand(Teams startingTeam, PlayerNumber startingPlayerNumber) {
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
     * Returns the player with the position of the passed parameters.
     * 
     * @param team
     *            Team of player
     * @param number
     *            Number of player
     * @return player objects
     */
    public Player getPlayer(Teams team, PlayerNumber number) {

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

    public Hand getHandOf(Teams team, PlayerNumber number) {
	return getPlayer(team, number).getHand();
    }

    private void dealOutCards() {
	Deal deal = deck.deal();

	redOne.setHand(deal.getRedOne());
	redTwo.setHand(deal.getRedTwo());
	blackOne.setHand(deal.getBlackOne());
	blackTwo.setHand(deal.getBlackTwo());
    }
}
