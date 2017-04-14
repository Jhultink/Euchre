package view;

/**
 * Java implemented 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GameController;
import models.GameModel;
import models.Player;

/**
 * This class handles all the UI and talks to the passed controllers.
 */
public class View implements ActionListener {

  /**
   * Bool to determine whether or not to show debug info.
   */
  private static final boolean IS_DEBUG = false;

  /** Controller for game. */
  private GameController controller;
  /** Main frame. */
  private JFrame frame;
  /** New game menu. */
  private JMenuItem newGameItem;
  /** Quit game option. */
  private JMenuItem quitGameItem;
  /** Save game option. */
  private JMenuItem saveGameItem;
  /** Load game option. */
  private JMenuItem loadGameItem;
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

  /**
   * @param newController
   *          Controller object
   * @param model
   *          GameModel object
   */
  public View(final GameController newController, final GameModel model) {
    
    // Set up JFrame
    frame = new JFrame("Euchre");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);

    this.controller = newController;
    this.gameModel = model;

    // Set up menu bar
    JMenuBar menu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");

    quitGameItem = new JMenuItem("Quit");
    quitGameItem.addActionListener(this);

    newGameItem = new JMenuItem("New Game");
    newGameItem.addActionListener(this);
    
    saveGameItem = new JMenuItem("Save Game");
    saveGameItem.addActionListener(this);
    
    loadGameItem = new JMenuItem("Load Game");
    loadGameItem.addActionListener(this);

    strategiesItem = new JMenuItem("Strategies");
    strategiesItem.addActionListener(this);

    aboutItem = new JMenuItem("About");
    aboutItem.addActionListener(this);

    helpMenu.add(aboutItem);
    helpMenu.add(strategiesItem);

    fileMenu.add(quitGameItem);
    fileMenu.add(newGameItem);
    fileMenu.add(saveGameItem);
    fileMenu.add(loadGameItem);

    menu.add(fileMenu);
    menu.add(helpMenu);

    frame.setJMenuBar(menu);

    topPanel = new PlayerPanel(BorderLayout.NORTH, this);
    rightPanel = new PlayerPanel(BorderLayout.EAST, this);
    bottomPanel = new PlayerPanel(BorderLayout.SOUTH, this);
    leftPanel = new PlayerPanel(BorderLayout.WEST, this);

    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

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

    Player[] players = model.getPlayersInOrder();
    
    // Clear panel and add cards
    bottomPanel.removeAll();
    bottomPanel.setPlayer(players[0]);

    // Clear panel and add cards
    leftPanel.removeAll();
    leftPanel.setPlayer(players[1]);
    
    // Clear panel and add cards
    topPanel.removeAll();
    topPanel.setPlayer(players[2]);

    // Clear panel and add cards
    rightPanel.removeAll();
    rightPanel.setPlayer(players[3]);
    

    centerPanel.removeAll();
    JPanel centerPanelOrganizer = new JPanel();
    centerPanelOrganizer.setLayout(
        new BoxLayout(centerPanelOrganizer, BoxLayout.Y_AXIS));

    if (IS_DEBUG) {
      centerPanelOrganizer.add(new JLabel(
          "Current Player: " + model.getCurrentPlayer().getTeam().name() + " "
              + model.getCurrentPlayer().getPlayerPosition().name()));
    }

    JLabel a = new JLabel("Current trump: " + model.getTrumpSuit());
    JLabel b = new JLabel("Calling team: " + model.getTeamWhoCalledTrump());
    JLabel c = new JLabel("Black hand score: " + model.getBlackHandScore());
    JLabel d = new JLabel("Red hand score: " + model.getRedHandScore());
    JLabel e = new JLabel("Black game score: " + model.getBlackGameScore());
    JLabel f = new JLabel("Red game score: " + model.getRedGameScore());
    
    a.setHorizontalAlignment(JLabel.CENTER);
    b.setHorizontalAlignment(JLabel.CENTER);
    c.setHorizontalAlignment(JLabel.CENTER);
    d.setHorizontalAlignment(JLabel.CENTER);
    e.setHorizontalAlignment(JLabel.CENTER);
    f.setHorizontalAlignment(JLabel.CENTER);
   
    a.setVerticalAlignment(JLabel.CENTER);
    b.setVerticalAlignment(JLabel.CENTER);
    c.setVerticalAlignment(JLabel.CENTER);
    d.setVerticalAlignment(JLabel.CENTER);
    e.setVerticalAlignment(JLabel.CENTER);
    f.setVerticalAlignment(JLabel.CENTER);
    
    centerPanel.add(a);
    centerPanel.add(b);
    centerPanel.add(c);
    centerPanel.add(d);
    centerPanel.add(e);
    centerPanel.add(f);

    centerPanelOrganizer.setBackground(Color.WHITE);

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
      frame.dispose();
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
    
    if (actionEvent.getSource() == saveGameItem) {
      JFileChooser fileChooser = new JFileChooser();
                 
      fileChooser.setSelectedFile(new File(
          new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
          .format(new Date()) + "_Euchre.sav"));

      if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
        
        try {
          
          File file = fileChooser.getSelectedFile();
                  
          ObjectOutputStream oos = new ObjectOutputStream(
              new BufferedOutputStream(new FileOutputStream(file)));
          oos.writeObject(gameModel);
          
          oos.close();
          
        } catch (Exception e) {
          JOptionPane.showMessageDialog(getFrame(), "Failed to save file");
          System.err.println(e.toString());
        }
        
      }
    }
    
    if (actionEvent.getSource() == loadGameItem) {
      
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileFilter(
          new FileNameExtensionFilter("Saved Files", "sav"));
      
      GameModel savedModel = null;
      
      if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
        try {
          File file = fileChooser.getSelectedFile();
          ObjectInputStream objectInputStream = new ObjectInputStream(
              new BufferedInputStream(new FileInputStream(file)));
          
          savedModel = (GameModel) objectInputStream.readObject();
          
          objectInputStream.close();

        } catch (Exception e) {
          JOptionPane.showMessageDialog(getFrame(), "Failed to open file");
          System.err.println(e.toString());
        }
        
        int option = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to open another game?");
        
        if (option == JOptionPane.OK_OPTION) {
          controller.loadGame(savedModel);
        }
      }
      
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
    if (event.getSource().equals(quitGameItem)) {
      frame.dispose();
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
   * Closes frame.
   */
  public void close() {
    frame.setVisible(false);
    frame.dispose();
  }

  /**
   * @return frame for centering JOption panes
   */
  public JFrame getFrame() {
    return this.frame;
  }
}

/**
 * Strategies window is opened up by view to display potential play strategies
 * to the user.
 * 
 * @author Ryan Jones, Jaredt Hultink, Keith Rodgers
 *
 */
class AboutWindow {

  /**
   * About window frame.
   */
  private JFrame frame;

  /**
   * opens about window.
   */
  AboutWindow() {
    frame = new JFrame("About the Game");
    frame.setSize(800, 600);
    JLabel label = new JLabel();
    StringBuffer labelText = new StringBuffer("<html>");
    try {
      Scanner inFile = new Scanner(new FileInputStream("src/about.txt"),
          "UTF-8");
      while (inFile.hasNextLine()) {
        labelText.append(inFile.nextLine() + "<br />");
      }
      inFile.close();
      labelText.append("</html>");
      label.setText(labelText.toString());
    } catch (Exception e) {
      System.out.println("ERROR; COULD NOT LOCATE FILE: 'about.txt'");
    }
    frame.add(label);
  }

  /**
   * Sets visible.
   */
  public void render() {
    frame.setVisible(true);
  }
}

/**
 * Strategies window is opened up by view to display potential play strategies
 * to the user.
 * 
 * @author Ryan Jones, Jaredt Hultink, Keith Rodgers
 *
 */
class StrategiesWindow {

  /**
   * 
   */
  private JFrame frame;

  /**
   * 
   */
  StrategiesWindow() {
    frame = new JFrame("Euchre Strategies");
    frame.setSize(1000, 600);
    JLabel label = new JLabel();
    StringBuffer labelText = new StringBuffer("<html>");
    try {
      Scanner inFile = new Scanner(new FileInputStream("src/strategies.txt"),
          "UTF-8");
      while (inFile.hasNextLine()) {
        labelText.append(inFile.nextLine() + "<br />");
      }
      inFile.close();
      labelText.append("</html>");
      label.setText(labelText.toString());
    } catch (Exception e) {
      System.out.println("ERROR; COULD NOT LOCATE FILE: 'about.txt'");
    }
    frame.add(label);
  }

  /**
   * Sets it to be visible.
   */
  public void render() {
    frame.setVisible(true);
  }
}
