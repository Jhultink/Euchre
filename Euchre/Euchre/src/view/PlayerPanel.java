package view;
/*
Java implemented packages.
*/

import java.awt.BorderLayout;

//import java.awt.BorderLayout;
//import java.awt.Button;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JFrame;
//import javax.imageio.ImageIO;
//import javax.management.modelmbean.ModelMBean;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Custom implemented packages.
 */
import controller.GameController;
import models.Card;
import models.GameModel;
import models.Player;
import models.Teams;

/**
 * JPanel that holds a player.
 */
public class PlayerPanel extends JPanel implements MouseListener {
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Player who owns this panel.
   */
  private Player panelPlayer;

  /**
   * Model for game.
   */
  private GameModel gameModel;

  /**
   * Controller for game.
   */
  private GameController controller;

  /**
   * View for game.
   */
  private View view;

  /**
   * Layout variable.
   */
  private String layout;

  /**
   * Constructor for playerpanel.
   * 
   * @throws Exception
   *           if invalid layout
   * @param pView
   *          view to be referenced
   * @param pLayout
   *          layout reference number
   */
  PlayerPanel(final String pLayout, final View pView) {
    
    this.layout = pLayout;
    this.view = pView;
    this.gameModel = pView.getGameModel();
    this.controller = pView.getController();

    if (layout.equals(BorderLayout.EAST) 
        || layout.equals(BorderLayout.WEST)) {
      this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    } else if (layout.equals(BorderLayout.NORTH) 
        || layout.equals(BorderLayout.SOUTH)) {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    } else {
      System.err.println("Invalid layout. Must be NORTH, SOUTH, EAST, OR WEST");
    }
  }

  /**
   * Return player belonging to the panel.
   * 
   * @return player
   */
  public Player getPlayer() {
    return this.panelPlayer;
  }

  /**
   * @param newPlayer
   *          player to assign
   */
  public void setPlayer(final Player newPlayer) {
    this.panelPlayer = newPlayer;
    refresh();
  }

  /**
   * 
   */
  private void refresh() {

    JPanel cardPanel = new JPanel();
    
    if (layout.equals(BorderLayout.EAST)
        || layout.equals(BorderLayout.WEST)) {
      cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
    } else if (layout.equals(BorderLayout.NORTH) 
        || layout.equals(BorderLayout.SOUTH)) {
      cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
    }

    cardPanel.add(Box.createHorizontalGlue()); // for spacing
    for (Card card : this.getPlayer().getHand().getCards()) {
      CardButton button = new CardButton(card, this.getPlayer());
      button.addMouseListener(this);
      button.isHorizontal();
      
      if (!gameModel.getCurrentPlayer().equals(getPlayer())) {
        button.setText("");
      }
      
      if (this.getPlayer().getTeam() == Teams.RED) {
        button.setBackground(Color.RED);
      } else {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      cardPanel.add(button);
    }
    cardPanel.add(Box.createHorizontalGlue()); // for spacing
    cardPanel.setBackground(Color.WHITE);


    if (gameModel.getCardsInPlay().getCard(getPlayer()) != null) {
      CardButton playedCard = new CardButton(
          gameModel.getCardsInPlay().getCard(getPlayer()), getPlayer());
      
           
      if (layout.equals(BorderLayout.NORTH) 
          || layout.equals(BorderLayout.WEST)) {
        this.add(cardPanel);
        this.add(playedCard);
      } else if (layout.equals(BorderLayout.EAST) 
          || layout.equals(BorderLayout.SOUTH)) {
        this.add(playedCard);
        this.add(cardPanel);
      }
      
      
    } else {
      this.add(cardPanel);
    }

  }

  /**
   * Handles case of user clicking a button.
   * 
   * @param event
   *          registered mouse event
   */
  public void mouseClicked(final MouseEvent event) {
    Object obj = event.getSource();

    if (obj instanceof CardButton) {
      CardButton clickedButton = (CardButton) obj;
      if (clickedButton.getOwner().equals(gameModel.getCurrentPlayer())) {
        Card clickedCard = clickedButton.getCard();
        if (gameModel.isValidPlay(clickedCard, clickedButton.getOwner())) {
          view.rotatePlayerArray();
          controller.playCard(clickedCard, clickedButton.getOwner());

          if (gameModel.getCardsInPlay().allPlayed()) {
            controller.trickOver();
          }
        } else {
          JOptionPane.showMessageDialog(view.getFrame(), "Invalid play");
        }
      } else {
        JOptionPane.showMessageDialog(view.getFrame(),
            "You can only play your cards");
      }
    }
  }

  @Override
  public void mouseEntered(final MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(final MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(final MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(final MouseEvent arg0) {
    // TODO Auto-generated method stub

  }
}
