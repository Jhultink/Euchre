package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
    this.setMaximumSize(new Dimension(75, 113));
    this.setMinimumSize(new Dimension(75, 113));
    this.setHorizontalTextPosition(SwingConstants.LEFT);

    try {
      BufferedImage buttonIcon = ImageIO.read(new File(
          "src/SmallCardImages/" + buttonCard.getCardStringValue() + ".png"));
      this.setIcon(new ImageIcon(buttonIcon));
      this.setBorder(BorderFactory.createEmptyBorder());
    } catch (Exception e) {
      // card is not found
      System.out.println(
          "Could not find card image for " + buttonCard.getCardStringValue());
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
