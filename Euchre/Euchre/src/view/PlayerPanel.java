package view;

import javax.swing.JPanel;
import models.Player;

public class PlayerPanel extends JPanel {
	private Player panelPlayer;

	/**
	 * Default Constructor for 
	 *
	PlayerPanel() {
		this.panelPlayer = null;
	}
	
	PlayerPanel(Player newPlayer) {
	    this.panelPlayer = newPlayer;
	}*/

	public Player getPlayer() {
		return this.panelPlayer;
	}

	public void setPlayer(Player newPlayer) {
		this.panelPlayer = newPlayer;
	}
}
