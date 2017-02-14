package view;

import controller.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import models.PlayerNumber;
import models.Teams;

public class View implements MouseListener {

    private JFrame frame;
    private JMenuBar menu;
    private JMenu fileMenu;
    private JMenuItem newGameItem;
    private JMenuItem quitGameItem;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private ArrayList<CardButton> listOfButtons;
    
    private GameModel game;

	/**
	 * Default Constructor for View Class.
	 */
	public View() {

		// Set up JFrame
		frame = new JFrame("Euchre");
		frame.setSize(800, 600);
		listOfButtons = new ArrayList<CardButton>();

		// Set up menu bar
		menu = new JMenuBar();
		fileMenu = new JMenu("File");
		quitGameItem = new JMenuItem("Quit");
		newGameItem = new JMenuItem("New Game");
		fileMenu.add(quitGameItem);
		fileMenu.add(newGameItem);
		menu.add(fileMenu);
		frame.setJMenuBar(menu);

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(Color.WHITE);

		rightPanel = new JPanel();
		rightPanel.setLayout(
				new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.WHITE);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(
				new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setBackground(Color.WHITE);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.WHITE);

		centerPanel = new JPanel();
		centerPanel.setLayout(
				new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setBackground(Color.BLACK);
		
		frame.setLayout(new BorderLayout());

		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setBackground(Color.WHITE);

	}

	/**
	 * Render class renders UI for the Euchre game.
	 * @param model model to be rendered
	 */
	public void render(final GameModel model) {

		frame.setVisible(true);
		this.game = model;
		
		// Clear panel and add cards
		topPanel.removeAll();
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		for (Card card : game.getHandOf(Teams.RED, PlayerNumber.FIRST).getCards()) {
			CardButton button = new CardButton(card);
			button.addMouseListener(this);
			listOfButtons.add(button);
			topPanel.add(button);
		}
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		// topPanel.revalidate();
		// topPanel.repaint();

		// Clear panel and add cards
		rightPanel.removeAll();
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		for (Card card : game.getHandOf(Teams.BLACK, PlayerNumber.SECOND).getCards()) {
			CardButton button = new CardButton(card);
			button.addMouseListener(this);
			listOfButtons.add(button);
			rightPanel.add(button);
		}
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		// rightPanel.revalidate();
		// rightPanel.repaint();

		// Clear panel and add cards
		bottomPanel.removeAll();
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		JLabel currentPlayer = new JLabel(game.getCurrentPlayer().toString() + ":   ");
		bottomPanel.add(currentPlayer);
		for (Card card : game.getHandOf(Teams.RED, PlayerNumber.SECOND).getCards()) {
			CardButton button = new CardButton(card);
			button.addMouseListener(this);
			listOfButtons.add(button);
			bottomPanel.add(button);
		}
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		// bottomPanel.revalidate();
		// bottomPanel.repaint();

		// Clear panel and add cards
		leftPanel.removeAll();
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		for (Card card : game.getHandOf(Teams.BLACK, PlayerNumber.FIRST).getCards()) {
			CardButton button = new CardButton(card);
			button.addMouseListener(this);
			listOfButtons.add(button);
			leftPanel.add(button);
		}
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		// leftPanel.revalidate();
		// leftPanel.repaint();

		centerPanel.removeAll();
		JPanel centerPanelOrganizer = new JPanel(new BorderLayout());
		if (game.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getBlackOneCard().toString()), 
					 BorderLayout.WEST);
		}
		if (game.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getRedOneCard().toString()),
					 BorderLayout.NORTH);
		}
		if (game.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getBlackTwoCard().toString()),
					 BorderLayout.EAST);
		}
		if (game.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(game.cardsInPlay
					.getRedTwoCard().toString()),
					 BorderLayout.SOUTH);
		}			
		centerPanel.add(centerPanelOrganizer);
		
		for (CardButton button : listOfButtons) {
		    if (button.getParent() != bottomPanel) {
			//button.setText("-=-=-=-=-=-=-"); Here's where it sets the text to a string
			button.setEnabled(false);
		    }
		}
		
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
		CardButton clickedButton = null;
		String buttonText = "";

		if (obj instanceof JButton) {
			clickedButton = (CardButton) obj;
		}

		if (clickedButton != null) {
			buttonText = clickedButton.getText();
			Card clickedCard = clickedButton.getCard();
			if (game.isValidCard(clickedCard)) {
				clickedButton.setEnabled(false);
				//centerPanel.add(comp)
			    
			}
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
}
