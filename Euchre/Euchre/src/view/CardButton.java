package view;

import javax.swing.JButton;
import models.Card;

/**
 * Class extending JButton to contain card information.
 * 
 * @author Ryan Jones, Keith Rodgers, Jaredt Hultink
 *
 */
public class CardButton extends JButton {
    private Card buttonCard;
    
    CardButton(String str) {
	this.setText(str);
    }
    
}
