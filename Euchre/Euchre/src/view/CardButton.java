package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import models.Card;
import models.Player;

/**
 * Class extending JButton to contain card information.
 * 
 * @author Ryan Jones, Keith Rodgers, Jaredt Hultink
 *
 */
public class CardButton extends JButton {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Button associated with this button.
   */
  private Card buttonCard;
  /**
   * Player who has this card.
   */
  private Player owner;

  /**
   * Constructor for the CardButton class.
   * 
   * @param c
   *          card parameter passed in
   * @param p
   *          player who has card
   */
  CardButton(final Card c, final Player p) {
    this.buttonCard = c;
    this.owner = p;
    this.setText(buttonCard.getCardStringValue());
    this.setMaximumSize(new Dimension(300, 100));
    this.setMinimumSize(new Dimension(200, 100));
    this.setHorizontalTextPosition(SwingConstants.LEFT);
    
    try {
      this.setIcon(new ImageIcon("src/smallcard.png"));
    } catch (Exception e) {
      // card is not found
    }
    
    this.validate();
  }

  /**
   * Method returns the card associated with this button.
   * 
   * @return card associated with the button pressed
   */
  public Card getCard() {
    return this.buttonCard;
  }

  /**
   * @return the owner
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * @param mOwner
   *          the owner to set
   */
  public void setOwner(final Player mOwner) {
    this.owner = mOwner;
  }
}
