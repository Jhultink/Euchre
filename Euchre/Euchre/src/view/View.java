package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.omg.CosNaming._BindingIteratorImplBase;

import controller.GameController;
import models.Card;
import models.GameModel;
import models.Player;
import models.Teams;

/**
 * This class handles all the UI and talks to the passed controllers.
 */
public class View implements ActionListener {

  /**
   * Bool to determine whether or not to show debug info.
   */
  private static final boolean IS_DEBUG = true;

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
  private GameModel gameModel;
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
    //frame.setResizable(false);
    centerButtons = new ArrayList<CardButton>();

    this.controller = newController;
    this.gameModel = model;
    this.playerArray = new Player[4];
    playerArray[0] = gameModel.getCurrentPlayer();
    playerArray[1] = gameModel.nextPlayer();
    playerArray[2] = gameModel.nextPlayer();
    playerArray[3] = gameModel.nextPlayer();
    gameModel.nextPlayer();

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

    topPanel = new PlayerPanel(BoxLayout.X_AXIS, this);
    rightPanel = new PlayerPanel(BoxLayout.Y_AXIS, this);
    bottomPanel = new PlayerPanel(BoxLayout.X_AXIS, this);
    leftPanel = new PlayerPanel(BoxLayout.Y_AXIS, this);

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
    this.gameModel = model;
    try {
      Image img = ImageIO.read(getClass().getResource("src/card.png"));
    } catch (Exception e) {
      System.out.println("ERROR: COULD NOT FIND CARD IMAGE");
    }

    // Clear panel and add cards
    topPanel.removeAll();
    topPanel.setPlayer(playerArray[2]);

    // Clear panel and add cards
    rightPanel.removeAll();
    rightPanel.setPlayer(playerArray[1]);

    // Clear panel and add cards
    bottomPanel.removeAll();
    bottomPanel.setPlayer(playerArray[0]);

    // Clear panel and add cards
    leftPanel.removeAll();
    leftPanel.setPlayer(playerArray[3]);

    centerPanel.removeAll();
    JPanel centerPanelOrganizer = new JPanel(new BorderLayout());

    if (IS_DEBUG) {

      JPanel debugPanel = new JPanel();

      debugPanel.add(new JLabel(
          "Current Player: " + model.getCurrentPlayer().getTeam().name() + " "
              + model.getCurrentPlayer().getPlayerPosition().name()));

      debugPanel.add(new JLabel("Current trump: " + model.getTrumpSuit()));
      debugPanel
          .add(new JLabel("Calling team: " + model.getTeamWhoCalledTrump()));

      centerPanelOrganizer.add(debugPanel, BorderLayout.CENTER);

    }

    centerPanelOrganizer.setBackground(Color.WHITE);
    centerPanelOrganizer.setMaximumSize(new Dimension(550, 500));
    centerPanelOrganizer.setMinimumSize(new Dimension(550, 500));
    centerPanel.add(centerPanelOrganizer);

    frame.revalidate();
    frame.repaint();

  }

  /**
   * @return controller for this view
   */
  public GameController getController() {
    return controller;
  }

  /**
   * @return model for this view
   */
  public GameModel getGameModel() {
    return gameModel;
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
    //
    // if (obj instanceof CardButton) {
    // CardButton clickedButton = (CardButton) obj;
    // if (clickedButton.getParent().equals(bottomPanel)) {
    // Card clickedCard = clickedButton.getCard();
    // if (gameModel.isValidPlay(clickedCard, clickedButton.getOwner())) {
    // rotatePlayerArray();
    // centerButtons.add(clickedButton);
    // controller.playCard(clickedCard, clickedButton.getOwner());
    //
    // if (gameModel.getCardsInPlay().allPlayed()) {
    // controller.trickOver();
    // }
    // }
    // }
    // }

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
  public void rotatePlayerArray() {
    Player[] tempArray = new Player[4];
    tempArray[0] = this.playerArray[1];
    tempArray[1] = this.playerArray[2];
    tempArray[2] = this.playerArray[3];
    tempArray[3] = this.playerArray[0];
    this.playerArray = tempArray;
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
