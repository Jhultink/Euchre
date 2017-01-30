package models;

public class Player {

	public PlayerNumber playerPosition;
	public Teams team;
	
	public Player(Teams team, PlayerNumber playerPosition) {
		this.team = team;
		this.playerPosition = playerPosition;
	}
}
