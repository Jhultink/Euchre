package models;

/**
 * Holds values for all four cards in play.
 *   
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class CardsInPlay {
	
	/** First red player's played card. */
	private Card redOneCard;
	
	/** Second red player's played card. */
	private Card redTwoCard;
	
	/** First black player's played card. */
	private Card blackOneCard;
	
	/** Seconds black player's played card. */
	private Card blackTwoCard;
	
	/**
	 * @return the redOneCard, null if no card is played yet
	 */
	public Card getRedOneCard() {
		return redOneCard;
	}
	
	/**
	 * @param mRedOneCard the mRedOneCard to set
	 */
	public void setRedOneCard(final Card mRedOneCard) {
		this.redOneCard = mRedOneCard;
	}
	
	/**
	 * @return the redTwoCard, null if no card is played yet
	 */
	public Card getRedTwoCard() {
		return redTwoCard;
	}
	
	/**
	 * @param mRedTwoCard the mRedTwoCard to set
	 */
	public void setRedTwoCard(final Card mRedTwoCard) {
		this.redTwoCard = mRedTwoCard;
	}
	
	/**
	 * @return the blackOneCard, null if no card is played yet
	 */
	public Card getBlackOneCard() {
		return blackOneCard;
	}
	
	/**
	 * @param mBlackOneCard the mBlackOneCard to set
	 */
	public void setBlackOneCard(final Card mBlackOneCard) {
		this.blackOneCard = mBlackOneCard;
	}
	
	/**
	 * @return the blackTwoCard, null if no card is played yet
	 */
	public Card getBlackTwoCard() {
		return blackTwoCard;
	}
	
	/**
	 * @param mBlackTwoCard the mBlackTwoCard to set
	 */
	public void setBlackTwoCard(final Card mBlackTwoCard) {
		this.blackTwoCard = mBlackTwoCard;
	}
	
	public void setCard(final Card mCard, Teams team, PlayerNumber playerNumber){
						
		if (team == Teams.RED) {
			if (playerNumber == PlayerNumber.FIRST) {
				setRedOneCard(mCard);
			} else {
				setRedTwoCard(mCard);
			}
		} else {
			if (playerNumber == PlayerNumber.SECOND)	{
				setBlackOneCard(mCard);
			} else {
				setBlackTwoCard(mCard);
			}
		}
	}
	
	/**
	 * .
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
	 * 
	 */
	public void clear() {
	    if (allPlayed()) {
		this.setBlackOneCard(null);
		this.setBlackTwoCard(null);
		this.setRedOneCard(null);
		this.setRedTwoCard(null);
	    }
	}
}
