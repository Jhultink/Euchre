package view;

import javax.swing.JPanel;
import models.*;

public class PlayerPanel extends JPanel {
	private Player panelPlayer;

	PlayerPanel() {
		this.panelPlayer = null;
	}

	public Player getPlayer() {
		return this.panelPlayer;
	}

	public void setPlayer(Player newPlayer) {
		this.panelPlayer = newPlayer;
	}
}
