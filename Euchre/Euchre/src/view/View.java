package view;

import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.Card;
import models.GameModel;
import models.Player;
import models.PlayerNumber;
import models.Teams;

public class View implements MouseListener, ActionListener {

    private GameController controller;
    private JFrame frame;
    private JMenuBar menu;
    private JMenu fileMenu;
    private JMenuItem newGameItem;
    private JMenuItem quitGameItem;
    private PlayerPanel topPanel;
    private PlayerPanel rightPanel;
    private PlayerPanel bottomPanel;
    private PlayerPanel leftPanel;
    private JPanel centerPanel;    
    private GameModel game;
    private Player r1;
    private Player r2;
    private Player b1;
    private Player b2;
    private Player[] playerArray;
    private ArrayList<CardButton> centerButtons;

	/**
	 * Default Constructor for View Class.
	 */
	public View(GameController paramController, GameModel model) {

		// Set up JFrame
		frame = new JFrame("Euchre");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		centerButtons = new ArrayList<CardButton>();

		this.controller = paramController;
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
		menu = new JMenuBar();
		fileMenu = new JMenu("File");
		quitGameItem = new JMenuItem("Quit");
		newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(this);
		quitGameItem.addActionListener(this);
		fileMenu.add(quitGameItem);
		fileMenu.add(newGameItem);
		menu.add(fileMenu);
		frame.setJMenuBar(menu);

		topPanel = new PlayerPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		rightPanel = new PlayerPanel();
		rightPanel.setLayout(
				new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		bottomPanel = new PlayerPanel();
		bottomPanel.setLayout(
				new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

		leftPanel = new PlayerPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		centerPanel = new JPanel();
		centerPanel.setLayout(
				new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		frame.setLayout(new BorderLayout());

		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * Render class renders UI for the Euchre game.
	 * @param model model to be rendered
	 */
	public void render(GameModel model) {	
	    	
		frame.setVisible(true);
		this.game = model;
		game.setCurrentPlayer(playerArray[0]);
		
		// Clear panel and add cards
		topPanel.removeAll();
		//set player assigned to panel
		topPanel.setPlayer(playerArray[2]);
		//set label for panel
		JLabel thirdPlayer = new JLabel(topPanel.getPlayer().toString() + ":   ");
		topPanel.add(thirdPlayer);
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		//for(Card card : r1.getHand().getCards()) {
		for (Card card : topPanel.getPlayer().getHand().getCards()) {
		    	CardButton button = new CardButton(card, topPanel.getPlayer());
			//new Player(Teams.RED, PlayerNumber.FIRST));
			if (topPanel.getPlayer().team == Teams.RED) {
			    button.setBackground(Color.RED);
			} else {
			    button.setBackground(Color.BLACK);
			    button.setForeground(Color.WHITE);
			}
			button.addMouseListener(this);
			topPanel.add(button);
		}
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		//topPanel.setBackground(Color.WHITE);
		topPanel.revalidate();
		topPanel.repaint();

		// Clear panel and add cards
		rightPanel.removeAll();
		rightPanel.setPlayer(playerArray[1]);
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		JLabel secondPlayer = new JLabel(rightPanel.getPlayer().toString() + ":   ");
		rightPanel.add(secondPlayer);
		for (Card card : (rightPanel.getPlayer().getHand().getCards())) {
			CardButton button = new CardButton(card, rightPanel.getPlayer()); 
			//new Player(Teams.BLACK, PlayerNumber.SECOND));
			if(rightPanel.getPlayer().team == Teams.RED) {
			    button.setBackground(Color.RED);
			} else {
			    button.setBackground(Color.BLACK);
			    button.setForeground(Color.WHITE);
			}
			button.addMouseListener(this);
			rightPanel.add(button);
		}
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		//rightPanel.setBackground(Color.WHITE);
		rightPanel.revalidate();
		rightPanel.repaint();

		// Clear panel and add cards
		bottomPanel.removeAll();
		bottomPanel.setPlayer(playerArray[0]);
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		JLabel currentPlayer = new JLabel(bottomPanel.getPlayer().toString() + ":   ");
		bottomPanel.add(currentPlayer);
		for (Card card : bottomPanel.getPlayer().getHand().getCards()) {
			CardButton button = new CardButton(card, bottomPanel.getPlayer());
			//new Player(Teams.RED, PlayerNumber.SECOND));
			if(bottomPanel.getPlayer().team == Teams.RED) {
			    button.setBackground(Color.RED);
			} else {
			    button.setBackground(Color.BLACK);
			    button.setForeground(Color.WHITE);
			}
			button.addMouseListener(this);
			bottomPanel.add(button);
		}
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		//bottomPanel.setBackground(Color.WHITE);
		bottomPanel.revalidate();
		bottomPanel.repaint();

		// Clear panel and add cards
		leftPanel.removeAll();
		leftPanel.setPlayer(playerArray[3]);
		JLabel fourthPlayer = new JLabel(leftPanel.getPlayer().toString() + ":");
		leftPanel.add(fourthPlayer);
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		for (Card card : leftPanel.getPlayer().getHand().getCards()) {
			CardButton button = new CardButton(card, leftPanel.getPlayer());
			//new Player(Teams.BLACK, PlayerNumber.FIRST));
			if(leftPanel.getPlayer().team == Teams.RED) {
			    button.setBackground(Color.RED);
			} else {
			    button.setBackground(Color.BLACK);
			    button.setForeground(Color.WHITE);
			}
			button.addMouseListener(this);
			leftPanel.add(button);
		}
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		//leftPanel.setBackground(Color.WHITE);
		leftPanel.revalidate();
		leftPanel.repaint();

		centerPanel.removeAll();
		 JPanel centerPanelOrganizer = new JPanel(new BorderLayout());
		
		if (game.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getBlackOneCard().getCardStringValue()), 
					 BorderLayout.WEST);
		}
		if (game.cardsInPlay.getRedOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getRedOneCard().getCardStringValue()),
					 BorderLayout.NORTH);
		}
		if (game.cardsInPlay.getBlackTwoCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getBlackTwoCard().getCardStringValue()),
					 BorderLayout.EAST);
		}
		if (game.cardsInPlay.getRedTwoCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getRedTwoCard().getCardStringValue()),
					 BorderLayout.SOUTH);
		}			
		//centerPanelOrganizer.setBackground(Color.WHITE);
		centerPanelOrganizer.setMaximumSize(new Dimension(550, 300));
		centerPanelOrganizer.setMinimumSize(new Dimension(550, 300));
		centerPanel.add(centerPanelOrganizer);
				
		frame.revalidate();
		frame.repaint();

	}

	/**
	 * Responds to click functions.
	 * 
	 * @param ae
	 *            System registered event
	 */
	public void actionPerformed(ActionEvent ae) {
	    if (ae.getSource() == quitGameItem) {
		System.exit(0);
	    }
	    
	    if (ae.getSource() == newGameItem) {
		controller.newGame();
	    }
	}

	/**
	 * Method responds to Mouse Click Currently only displays text of button
	 * clicked.
	 * 
	 * @param event
	 *            MouseEvent registered
	 */
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		
		if (obj instanceof JButton) {	
			CardButton clickedButton = (CardButton) obj;	
			if (clickedButton.getParent().equals(bottomPanel)) {
				Card clickedCard = clickedButton.getCard();
				if (game.isValidPlay(clickedCard, clickedButton.getOwner())) {
					playerArray = rotatePlayerArray(playerArray);
					centerButtons.add(clickedButton);
					controller.playCard(clickedCard, clickedButton.getOwner());
					
					//THIS RUNS AFTER PLAYING ONLY THREE CARDS.
					if (game.cardsInPlay.allPlayed()) {
					    JOptionPane.showMessageDialog(frame, 
						    "The round is over. Clearing board.");
					    controller.clearTable();
					    game.clearTable();
					    centerPanel.removeAll();
					    frame.revalidate();
					    frame.repaint();
					    //controller.refresh();
					}
				}
			} else {
			    JOptionPane.showMessageDialog(frame, "ONLY BOTTOM CAN PLAY CARDS");
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
	 *            Registered Mouse Event
	 */
	public void mouseEntered(MouseEvent event) {

	}

	/**
	 * Mouse Event to run when mouse event is exited.
	 * 
	 * @param event
	 *            Registered Mouse Event
	 */
	public void mouseExited(MouseEvent event) {

	}

	/**
	 * Mouse Event to run when mouse event is exited.
	 * 
	 * @param event
	 *            Registered Mouse Event
	 */
	public void mouseReleased(MouseEvent event) {

	}

	/**
	 * Mouse Event to run when mouse event is exited.
	 * 
	 * @param event
	 *            Registered Mouse Event
	 */
	public void mousePressed(MouseEvent event) {

	}
	
	/**
	 * Rotates the player array.
	 * @param arrayToRotate Array to be rotated
	 * @return rotated array
	 */
	public Player[] rotatePlayerArray(Player[] arrayToRotate) {
	    Player[] tempArray = new Player[4];
	    tempArray[0] = arrayToRotate[1];
	    tempArray[1] = arrayToRotate[2];
	    tempArray[2] = arrayToRotate[3];
	    tempArray[3] = arrayToRotate[0];
	    return tempArray;
	}
	
	public void close() {
	    frame.dispose();
	}
}
