package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
   * View for game
   */
  private View view;
  /**
   * Layout
   */
  private int layout;

  /**
   * @return player associated with this panel
   * @throws Exception
   *           if invalid layout
   */
  PlayerPanel(int pLayout, View pView) {
    this.layout = pLayout;
    this.view = pView;
    this.gameModel = pView.getGameModel();
    this.controller = pView.getController();

    if (layout == BoxLayout.X_AXIS) {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    } else if (layout == BoxLayout.Y_AXIS) {
      this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    } else {
      System.err.println("Invalid layout. Must be X_AXIS or Y_AXIS");
    }
  }

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

  private void refresh() {

    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BoxLayout(cardPanel, layout));

    for (Card card : this.getPlayer().getHand().getCards()) {
      CardButton button = new CardButton(card, this.getPlayer());
      /*
       * try { Image img = ImageIO.read(getClass().getResource("src/card.png"));
       * button = new CardButton(card, this.getPlayer(), img); } catch
       * (Exception e) {
       * //System.out.println("ERROR: COULD NOT FIND CARD IMAGE"); }
       */
      button.addMouseListener(this);
      button.isHorizontal();
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

    this.add(cardPanel);

    if (gameModel.getCardsInPlay().getCard(getPlayer()) != null) {
      CardButton playedCard = new CardButton(
          gameModel.getCardsInPlay().getCard(getPlayer()), getPlayer());
      this.add(playedCard);
    }

  }

  @Override
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
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }
}
