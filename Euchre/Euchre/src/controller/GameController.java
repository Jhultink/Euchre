package controller;

import view.View;

import javax.swing.JOptionPane;

import models.Card;
import models.GameModel;
import models.Player;
import models.PlayerNumber;
import models.Teams;

/**
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class GameController {

	/**
	 * Model that holds all the data about the game.
	 */
	private GameModel model;

	/**
	 * View class that renders the model.
	 */
	private View view;

	/**
	 * Sets up a new model and view.
	 */
	GameController() {

		this.model = new GameModel();
		this.view = new View(this, model);
	}

	/**
	 * Starts the game with Red One
	 */
	public void start() {
		model.newHand(Teams.RED, PlayerNumber.FIRST);
		view.render(model);
		selectTrump(model);
	}

	/**
	 * Checks if played card is valid and puts it in play if it is.
	 * 
	 * @param chosenCard
	 *            card to check
	 * @param player
	 *            player who played card
	 */
	public void playCard(Card chosenCard, Player player) {
		if (model.isValidPlay(chosenCard, player)) {
			model.getCurrentPlayer().getHand().getCards().remove(chosenCard);

			model.cardsInPlay.setCard(chosenCard, model.getCurrentTeam(), model.getCurrentPlayerNumber());
		}
		view.render(model);
	}

	/**
	 * Called by view when trick is over
	 */
	public void trickOver()
	{
		JOptionPane.showMessageDialog(view.getFrame(), "Trick over");		
		clearTable();		
		selectTrump(model);
	}	
	/**
	 *  Creates new game
	 */
	public void newGame() {
		view.close();
		this.model = new GameModel();
		this.view = new View(this, model);
		this.start();
	}

	/**
	 * .
	 */
	public void refresh() {
		view.render(model);
	}

	/**
	 * clears the table
	 */
	public void clearTable() {
		this.model.clearTable();
		view.close();
		view = new View(this, model);
		refresh();
	}

	/**
	 * Select trumps and sets up the passed model
	 */
	public void selectTrump(GameModel model)
	{
		boolean isTrumpSelected = false;
		boolean allPlayersPassed = false;
		int playersPassed = 0;
		
		while(!isTrumpSelected && !allPlayersPassed)
		{						
			String[] buttons = {"Take card", "Pass" };
			String displayMessage = model.getCurrentTeam().name() + " " + model.getCurrentPlayerNumber().name()
					+ ", do you want to take this card? \n\n\t " + model.getTrumpCard().getCardStringValue();
			
			int returnValue = JOptionPane.showOptionDialog(view.getFrame(), displayMessage, "Select trump",
			        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
			
			isTrumpSelected = (returnValue == 0);
			
			model.nextPlayer();
			playersPassed++;
			allPlayersPassed = (playersPassed == 4);			
		}
		
		
	}
	
}
