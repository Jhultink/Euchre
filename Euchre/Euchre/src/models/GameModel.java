package models;

/**
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
	 * Initializes the four players.
	 */
	public GameModel() {

		blackOne = new Player(Teams.BLACK, PlayerNumber.FIRST);
		blackTwo = new Player(Teams.BLACK, PlayerNumber.SECOND);
		redOne = new Player(Teams.RED, PlayerNumber.FIRST);
		redTwo = new Player(Teams.RED, PlayerNumber.SECOND);	
	}
	
	/**
	 * Returns player who's turn it currently is.
	 * @return current player
	 */
	public Player getCurrentPlayer() {
		return getPlayer(currentTeam, currentPlayerNumber);
	}
	
	/**
	 * Returns the player with the position of the passed parameters.
	 * @param team Team of player
	 * @param number Number of player
	 * @return player objects
	 */
	public Player getPlayer(Teams team, PlayerNumber number){
		
		if(team == Teams.BLACK){
			if(number == PlayerNumber.FIRST){
				return blackOne;
			}else {
				return blackTwo;
			}
		}
		else{
			if(number == PlayerNumber.FIRST){
				return redOne;
			}else {
				return redTwo;
			}
		}
	}
}
