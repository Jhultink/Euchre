package view;

import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.util.*;

import controller.*;
import models.*;

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

    /**
     * Default Constructor for View Class.
     */
    public View() {

	// Set up JFrame
	frame = new JFrame("Euchre");
	frame.setSize(800, 600);

	// Set up menu bar
	menu = new JMenuBar();
	fileMenu = new JMenu("File");
	quitGameItem = new JMenuItem();
	newGameItem = new JMenuItem();
	fileMenu.add(quitGameItem);
	fileMenu.add(newGameItem);
	menu.add(fileMenu);
	frame.setJMenuBar(menu);

	topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

	rightPanel = new JPanel();
	rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

	bottomPanel = new JPanel();
	bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

	leftPanel = new JPanel();
	leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

	frame.setLayout(new BorderLayout());

	frame.add(topPanel, BorderLayout.NORTH);
	frame.add(rightPanel, BorderLayout.EAST);
	frame.add(bottomPanel, BorderLayout.SOUTH);
	frame.add(leftPanel, BorderLayout.WEST);

	frame.setVisible(true);
    }

    /**
     * Render class renders UI for the Euchre game.
     */
    public void render(GameModel model) {

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
	JButton testButton = null;
	String buttonText = "";

	if (obj instanceof JButton) {
	    testButton = (JButton) obj;
	}

	if (testButton != null) {
	    buttonText = testButton.getText();
	    JOptionPane.showMessageDialog(frame, buttonText);
	    testButton.setEnabled(false);
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
