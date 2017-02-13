package view;

import java.awt.Dimension;

import javax.swing.*;
import models.Card;

/**
 * Class extending JButton to contain card information.
 * 
 * @author Ryan Jones, Keith Rodgers, Jaredt Hultink
 *
 */
public class CardButton extends JButton {
    private Card buttonCard;
    
    /**
     * Constructor for the CardButton class.
     * @param c card paramater passed in
     */
    CardButton(Card c) {
	this.buttonCard = c;
	this.setText(buttonCard.getCardStringValue());
	//this.setPreferredSize(new Dimension(150, 100));
	//this.setSize(new Dimension(100, 50));
	this.setHorizontalTextPosition(SwingConstants.LEFT);
    }
    
    /**
     * Method returns the card associated with this button.
     * @return card associated with the button pressed
     */
    Card getCard() {
	return this.buttonCard;
    }
    
}
