package view;

import java.awt.Dimension;

import javax.swing.*;
import models.Card;
import models.Player;

/**
 * Class extending JButton to contain card information.
 * 
 * @author Ryan Jones, Keith Rodgers, Jaredt Hultink
 *
 */
public class CardButton extends JButton {
	private Card buttonCard;
	private Player owner;

	/**
	 * Constructor for the CardButton class.
	 * 
	 * @param c
	 *            card parameter passed in
	 */
	CardButton(Card c, Player p) {
		this.buttonCard = c;
		this.owner = p;
		this.setText(buttonCard.getCardStringValue());
		this.setMaximumSize(new Dimension(150, 50));
		this.setHorizontalTextPosition(SwingConstants.LEFT);
		this.validate();
	}

	/**
	 * Method returns the card associated with this button.
	 * 
	 * @return card associated with the button pressed
	 */
	Card getCard() {
		return this.buttonCard;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

}
