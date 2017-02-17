package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.GameController;
import models.Card;
import models.GameModel;
import models.Player;
import models.PlayerNumber;
import models.Teams;

/**
 * This class handles all the UI and talks to the passed controllers.
 */
public class View implements MouseListener, ActionListener {

  /** Controller for game. */
  private GameController controller;
  /** Main frame. */
  private JFrame frame;
  /** New game menu. */
  private JMenuItem newGameItem;
  /** Quit game option. */
  private JMenuItem quitGameItem;
  /** Strategies option. */
  private JMenuItem strategiesItem;
  /** About item. */
  private JMenuItem aboutItem;
  /** Top panel. */
  private PlayerPanel topPanel;
  /** Panel for right player. */
  private PlayerPanel rightPanel;
  /** Panel for bottom player. */
  private PlayerPanel bottomPanel;
  /** Panel for left player. */
  private PlayerPanel leftPanel;
  /** panel for playing cards. */
  private JPanel centerPanel;
  /** Model that hold all the game info. */
  private GameModel game;
  /** Red one player. */
  private Player r1;
  /** Red two player. */
  private Player r2;
  /** Black one player. */
  private Player b1;
  /** Black two player. */
  private Player b2;
  /** Array of all players. */
  private Player[] playerArray;
  /** All buttons in the center. */
  private ArrayList<CardButton> centerButtons;

  /**
   * .
   * 
   * @param newController
   *          Controller object
   * @param model
   *          GameModel object
   */
  public View(final GameController newController, final GameModel model) {

    // Set up JFrame
    frame = new JFrame("Euchre");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setResizable(false);
    centerButtons = new ArrayList<CardButton>();

    this.controller = newController;
    this.game = model;
    this.playerArray = new Player[4];
    r1 = game.getPlayer(Teams.RED, PlayerNumber.FIRST);
    r2 = game.getPlayer(Teams.RED, PlayerNumber.SECOND);
    b1 = game.getPlayer(Teams.BLACK, PlayerNumber.FIRST);
    b2 = game.getPlayer(Teams.BLACK, PlayerNumber.SECOND);
    playerArray[0] = r1;
    playerArray[1] = b1;
    playerArray[2] = r2;
    playerArray[3] = b2;

    // Set up menu bar
    JMenuBar menu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");

    quitGameItem = new JMenuItem("Quit");
    quitGameItem.addActionListener(this);

    newGameItem = new JMenuItem("New Game");
    newGameItem.addActionListener(this);

    strategiesItem = new JMenuItem("Strategies");
    strategiesItem.addActionListener(this);

    aboutItem = new JMenuItem("About");
    aboutItem.addActionListener(this);

    helpMenu.add(aboutItem);
    helpMenu.add(strategiesItem);

    fileMenu.add(quitGameItem);
    fileMenu.add(newGameItem);

    menu.add(fileMenu);
    menu.add(helpMenu);

    frame.setJMenuBar(menu);

    topPanel = new PlayerPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

    rightPanel = new PlayerPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    bottomPanel = new PlayerPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

    leftPanel = new PlayerPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

    frame.setLayout(new BorderLayout());

    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(rightPanel, BorderLayout.EAST);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.add(leftPanel, BorderLayout.WEST);
    frame.add(centerPanel, BorderLayout.CENTER);

  }

  /**
   * Render class renders UI for the Euchre game.
   * 
   * @param model
   *          model to be rendered
   */
  public void render(final GameModel model) {

    frame.setVisible(true);
    this.game = model;
    game.setCurrentPlayer(playerArray[0]);

    // Clear panel and add cards
    topPanel.removeAll();

    // set player assigned to panel
    topPanel.setPlayer(playerArray[2]);

    // set label for panel
    JLabel thirdPlayer = new JLabel(topPanel.getPlayer().toString() + ":   ");
    topPanel.add(thirdPlayer);
    topPanel.add(Box.createHorizontalGlue()); // for spacing

    for (Card card : topPanel.getPlayer().getHand().getCards()) {
      CardButton button = new CardButton(card, topPanel.getPlayer());
      button.isHorizontal();
      if (topPanel.getPlayer().getTeam() == Teams.RED) {
        button.setBackground(Color.RED);
      } else {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      topPanel.add(button);
    }
    topPanel.add(Box.createHorizontalGlue()); // for spacing
    topPanel.setBackground(Color.WHITE);

    // Clear panel and add cards
    rightPanel.removeAll();
    rightPanel.setPlayer(playerArray[1]);
    rightPanel.add(Box.createVerticalGlue()); // for spacing
    JLabel secondPlayer = new JLabel(
        rightPanel.getPlayer().toString() + ":   ");
    rightPanel.add(secondPlayer);

    for (Card card : (rightPanel.getPlayer().getHand().getCards())) {
      CardButton button = new CardButton(card, rightPanel.getPlayer());
      button.isVertical();
      if (rightPanel.getPlayer().getTeam() == Teams.RED) {
        button.setBackground(Color.RED);
      } else {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      rightPanel.add(button);
    }
    rightPanel.add(Box.createVerticalGlue()); // for spacing
    rightPanel.setBackground(Color.WHITE);

    // Clear panel and add cards
    bottomPanel.removeAll();
    bottomPanel.setPlayer(playerArray[0]);
    bottomPanel.add(Box.createHorizontalGlue()); // for spacing
    JLabel currentPlayer = new JLabel(
        bottomPanel.getPlayer().toString() + ":   ");
    bottomPanel.add(currentPlayer);
    for (Card card : bottomPanel.getPlayer().getHand().getCards()) {
      CardButton button = new CardButton(card, bottomPanel.getPlayer());
      button.isHorizontal();
      if (bottomPanel.getPlayer().getTeam() == Teams.RED) {
        button.setBackground(Color.RED);
      } else {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      button.addMouseListener(this);
      bottomPanel.add(button);
    }
    bottomPanel.add(Box.createHorizontalGlue()); // for spacing
    bottomPanel.setBackground(Color.WHITE);

    // Clear panel and add cards
    leftPanel.removeAll();
    leftPanel.setPlayer(playerArray[3]);
    JLabel fourthPlayer = new JLabel(leftPanel.getPlayer().toString() + ":");
    leftPanel.add(fourthPlayer);
    leftPanel.add(Box.createVerticalGlue()); // for spacing
    for (Card card : leftPanel.getPlayer().getHand().getCards()) {
      CardButton button = new CardButton(card, leftPanel.getPlayer());
      button.isVertical();
      if (leftPanel.getPlayer().getTeam() == Teams.RED) {
        button.setBackground(Color.RED);
      } else {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      leftPanel.add(button);
    }
    leftPanel.add(Box.createVerticalGlue()); // for spacing
    leftPanel.setBackground(Color.WHITE);

    centerPanel.removeAll();
    JPanel centerPanelOrganizer = new JPanel(new BorderLayout());

    if (game.getCardsInPlay().getBlackOneCard() != null) {
      centerPanelOrganizer.add(
          new Button(
              game.getCardsInPlay().getBlackOneCard().getCardStringValue()),
          BorderLayout.WEST);
    }
    if (game.getCardsInPlay().getRedOneCard() != null) {
      centerPanelOrganizer.add(
          new Button(
              game.getCardsInPlay().getRedOneCard().getCardStringValue()),
          BorderLayout.NORTH);
    }
    if (game.getCardsInPlay().getBlackTwoCard() != null) {
      centerPanelOrganizer.add(
          new Button(
              game.getCardsInPlay().getBlackTwoCard().getCardStringValue()),
          BorderLayout.EAST);
    }
    if (game.getCardsInPlay().getRedTwoCard() != null) {
      centerPanelOrganizer.add(
          new Button(
              game.getCardsInPlay().getRedTwoCard().getCardStringValue()),
          BorderLayout.SOUTH);
    }
    centerPanelOrganizer.setBackground(Color.WHITE);
    centerPanelOrganizer.setMaximumSize(new Dimension(550, 500));
    centerPanelOrganizer.setMinimumSize(new Dimension(550, 500));
    centerPanel.add(centerPanelOrganizer);

    frame.revalidate();
    frame.repaint();

  }

  /**
   * Responds to click functions.
   * 
   * @param actionEvent
   *          System registered event
   */
  public void actionPerformed(final ActionEvent actionEvent) {
    if (actionEvent.getSource() == quitGameItem) {
      System.exit(0);
    }

    if (actionEvent.getSource() == newGameItem) {
      controller.newGame();
    }

    if (actionEvent.getSource() == aboutItem) {
      // Open new window
      AboutWindow about = new AboutWindow();
      about.render();
    }

    if (actionEvent.getSource() == strategiesItem) {
      // Open new window
      StrategiesWindow strat = new StrategiesWindow();
      strat.render();
    }
  }

  /**
   * Method responds to Mouse Click Currently only displays text of button
   * clicked.
   * 
   * @param event
   *          MouseEvent registered
   */
  public void mouseClicked(final MouseEvent event) {
    Object obj = event.getSource();

    if (obj instanceof CardButton) {
      CardButton clickedButton = (CardButton) obj;
      if (clickedButton.getParent().equals(bottomPanel)) {
        Card clickedCard = clickedButton.getCard();
        if (game.isValidPlay(clickedCard, clickedButton.getOwner())) {
          playerArray = rotatePlayerArray(playerArray);
          centerButtons.add(clickedButton);
          controller.playCard(clickedCard, clickedButton.getOwner());

          if (game.getCardsInPlay().allPlayed()) {
            controller.trickOver();
          }
        }
      }
    }

    if (event.getSource().equals(quitGameItem)) {
      System.exit(0);
    }
  }

  /**
   * Mouse Event to run when mouse event is exited.
   * 
   * @param event
   *          Registered Mouse Event
   */
  public void mouseEntered(final MouseEvent event) {

  }

  /**
   * Mouse Event to run when mouse event is exited.
   * 
   * @param event
   *          Registered Mouse Event
   */
  public void mouseExited(final MouseEvent event) {

  }

  /**
   * Mouse Event to run when mouse event is exited.
   * 
   * @param event
   *          Registered Mouse Event
   */
  public void mouseReleased(final MouseEvent event) {

  }

  /**
   * Mouse Event to run when mouse event is exited.
   * 
   * @param event
   *          Registered Mouse Event
   */

  public void mousePressed(final MouseEvent event) {

  }

  /**
   * Rotates the player array.
   * 
   * @param arrayToRotate
   *          Array to be rotated
   * @return rotated array
   */
  public Player[] rotatePlayerArray(final Player[] arrayToRotate) {
    Player[] tempArray = new Player[4];
    tempArray[0] = arrayToRotate[1];
    tempArray[1] = arrayToRotate[2];
    tempArray[2] = arrayToRotate[3];
    tempArray[3] = arrayToRotate[0];
    return tempArray;
  }

  /**
   * .
   */
  public void close() {
    frame.dispose();
  }

  /**
   * @return frame for centering JOption panes
   */
  public JFrame getFrame() {
    return this.frame;
  }
}
