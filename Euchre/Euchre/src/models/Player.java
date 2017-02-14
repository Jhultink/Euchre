package models;

public class Player {

    public PlayerNumber playerPosition;
    public Teams team;
    public Hand hand;

    /**
     * Constructor for Player class initializes player's team and position.
     * @param team
     *            team of player
     * @param playerPosition
     *            position of player
     */
    public Player(Teams team, PlayerNumber playerPosition) {
	this.team = team;
	this.playerPosition = playerPosition;
    }

    public Hand getHand() {
	return hand;
    }

    public void setHand(Hand hand) {
	this.hand = hand;
    }
    
    public String toString() {
	return team + " " + playerPosition;
    }
        
    @Override
    public boolean equals(Object obj) {
    	if (obj == null || !(obj instanceof Player)) {
			return false;
		}
    	
    	Player player = (Player) obj;
    	
    	return this.team == player.team 
    			&& this.playerPosition == player.playerPosition;
    }

}
