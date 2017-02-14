package view;

import javax.swing.JPanel;
import models.*;

public class PlayerPanel extends JPanel {
    private Player panelPlayer;
    
    PlayerPanel(Player p) {
	this.panelPlayer = p;
    }
    
    public Player getPlayer() {
	return this.panelPlayer;
    }
}
