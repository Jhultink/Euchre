package view;

import javax.swing.JPanel;
import models.Player;
/**
 * JPanel that holds a player.
 */
public class PlayerPanel extends JPanel {
	/**
   * Serial ID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Player who owns this panel.
   */
  private Player panelPlayer;
	/**
	 * @return player associated with this panel
	 */
	public Player getPlayer() {
		return this.panelPlayer;
	}
/**
 * @param newPlayer player to assign
 */
	public void setPlayer(final Player newPlayer) {
		this.panelPlayer = newPlayer;
	}
}
