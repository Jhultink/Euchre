package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
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

<<<<<<< HEAD
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
=======
    private JFrame frame;
    private JMenuBar menu;
    private JMenu fileMenu;
    private JMenuItem newGameItem;
    private JMenuItem quitGameItem;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    
    private GameModel game;
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git

    /**
     * Default Constructor for View Class.
     */
    public View() {

	// Set up JFrame
	frame = new JFrame("Euchre");
	frame.setSize(800, 600);
	Container gameContainer = frame.getContentPane();
	gameContainer.setBackground(Color.WHITE);

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
	topPanel.setBackground(Color.WHITE);
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

<<<<<<< HEAD
		rightPanel = new JPanel();
		rightPanel.setLayout(
				new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
=======
	rightPanel = new JPanel();
	rightPanel.setBackground(Color.WHITE);
	rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git

<<<<<<< HEAD
		bottomPanel = new JPanel();
		bottomPanel.setLayout(
				new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
=======
	bottomPanel = new JPanel();
	bottomPanel.setBackground(Color.WHITE);
	bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git

	leftPanel = new JPanel();
	leftPanel.setBackground(Color.WHITE);
	leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

<<<<<<< HEAD
		centerPanel = new JPanel();
		centerPanel.setLayout(
				new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		frame.setLayout(new BorderLayout());
=======
	frame.setLayout(new BorderLayout());
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git

<<<<<<< HEAD
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
=======
	frame.add(topPanel, BorderLayout.NORTH);
	frame.add(rightPanel, BorderLayout.EAST);
	frame.add(bottomPanel, BorderLayout.SOUTH);
	frame.add(leftPanel, BorderLayout.WEST);
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git

<<<<<<< HEAD
=======
    }

    /**
     * Render class renders UI for the Euchre game.
     */
    public void render(GameModel model) {
	this.game = model;

	frame.setVisible(true);

	// Clear panel and add cards
	topPanel.removeAll();
	topPanel.add(Box.createHorizontalGlue()); // for spacing
	for (Card card : model.getHandOf(Teams.RED, PlayerNumber.FIRST).getCards()) {
	    CardButton button = new CardButton(card);
	    button.addMouseListener(this);
	    button.setBackground(Color.RED);
	    topPanel.add(button);
	}
	topPanel.add(Box.createHorizontalGlue()); // for spacing
	// topPanel.revalidate();
	// topPanel.repaint();

	// Clear panel and add cards
	rightPanel.removeAll();
	rightPanel.add(Box.createVerticalGlue()); // for spacing
	for (Card card : model.getHandOf(Teams.BLACK, PlayerNumber.SECOND).getCards()) {
	    CardButton button = new CardButton(card);
	    button.addMouseListener(this);
	    button.setBackground(Color.RED);
	    rightPanel.add(button);
	}
	rightPanel.add(Box.createVerticalGlue()); // for spacing
	// rightPanel.revalidate();
	// rightPanel.repaint();

	// Clear panel and add cards
	bottomPanel.removeAll();
	bottomPanel.add(Box.createHorizontalGlue()); // for spacing
	for (Card card : model.getHandOf(Teams.RED, PlayerNumber.SECOND).getCards()) {
	    CardButton button = new CardButton(card);
	    button.addMouseListener(this);
	    button.setBackground(Color.GREEN);
	    bottomPanel.add(button);
	}
	bottomPanel.add(Box.createHorizontalGlue()); // for spacing
	// bottomPanel.revalidate();
	// bottomPanel.repaint();

	// Clear panel and add cards
	leftPanel.removeAll();
	leftPanel.add(Box.createVerticalGlue()); // for spacing
	for (Card card : model.getHandOf(Teams.BLACK, PlayerNumber.FIRST).getCards()) {
	    CardButton button = new CardButton(card);
	    button.addMouseListener(this);
	    button.setBackground(Color.RED);
	    leftPanel.add(button);
	}
	leftPanel.add(Box.createVerticalGlue()); // for spacing
	// leftPanel.revalidate();
	// leftPanel.repaint();

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
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git
	}

<<<<<<< HEAD
	/**
	 * Render class renders UI for the Euchre game.
	 * @param model model to be rendered
	 */
	public void render(final GameModel model) {

		frame.setVisible(true);
		
		// Clear panel and add cards
		topPanel.removeAll();
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		for (Card card : model.getHandOf(Teams.RED, PlayerNumber.FIRST).getCards()) {
			JButton button = new JButton(card.getCardStringValue());
			button.addMouseListener(this);
			topPanel.add(button);
		}
		topPanel.add(Box.createHorizontalGlue()); // for spacing
		// topPanel.revalidate();
		// topPanel.repaint();

		// Clear panel and add cards
		rightPanel.removeAll();
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		for (Card card : model.getHandOf(Teams.BLACK, PlayerNumber.SECOND).getCards()) {
			JButton button = new JButton(card.getCardStringValue());
			button.addMouseListener(this);
			rightPanel.add(button);
		}
		rightPanel.add(Box.createVerticalGlue()); // for spacing
		// rightPanel.revalidate();
		// rightPanel.repaint();

		// Clear panel and add cards
		bottomPanel.removeAll();
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		for (Card card : model.getHandOf(Teams.RED, PlayerNumber.SECOND).getCards()) {
			JButton button = new JButton(card.getCardStringValue());
			button.addMouseListener(this);
			bottomPanel.add(button);
		}
		bottomPanel.add(Box.createHorizontalGlue()); // for spacing
		// bottomPanel.revalidate();
		// bottomPanel.repaint();

		// Clear panel and add cards
		leftPanel.removeAll();
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		for (Card card : model.getHandOf(Teams.BLACK, PlayerNumber.FIRST).getCards()) {
			JButton button = new JButton(card.getCardStringValue());
			button.addMouseListener(this);
			leftPanel.add(button);
		}
		leftPanel.add(Box.createVerticalGlue()); // for spacing
		// leftPanel.revalidate();
		// leftPanel.repaint();

		centerPanel.removeAll();
		JPanel centerPanelOrganizer = new JPanel(new BorderLayout());
		if (model.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(model.cardsInPlay
					.getBlackOneCard().toString()), 
					 BorderLayout.WEST);
		}
		if (model.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(model.cardsInPlay
					.getRedOneCard().toString()),
					 BorderLayout.NORTH);
		}
		if (model.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(model.cardsInPlay
					.getBlackTwoCard().toString()),
					 BorderLayout.EAST);
		}
		if (model.cardsInPlay.getBlackOneCard() != null) {
			centerPanelOrganizer.add(new Button(model.cardsInPlay
					.getRedTwoCard().toString()),
					 BorderLayout.SOUTH);
		}
			
		centerPanel.add(centerPanelOrganizer);
		
		frame.revalidate();
		frame.repaint();

=======
	if (clickedButton != null) {
	    
	    if (game.isValidCard(clickedButton.getCard())) {
		//Stuff from gamemodel
	    }
	    
	    buttonText = clickedButton.getText();
	    JOptionPane.showMessageDialog(frame, buttonText);
	    clickedButton.setEnabled(false);
	    
	    
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git
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
