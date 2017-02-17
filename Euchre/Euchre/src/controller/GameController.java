package controller;

import view.View;

import java.awt.image.RescaleOp;

import javax.swing.JOptionPane;

import models.Card;
import models.GameModel;
import models.Player;
import models.PlayerNumber;
import models.Suit;
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
	 * Starts the game with Red One.
	 */
	public void start() {
		model.newHand(Teams.RED, PlayerNumber.FIRST);
		view.render(model);
		selectTrump();
	}

	/**
	 * Checks if played card is valid and puts it in play if it is.
	 * 
	 * @param chosenCard
	 *            card to check
	 * @param player
	 *            player who played card
	 */
	public void playCard(final Card chosenCard, final Player player) {
		if (model.isValidPlay(chosenCard, player)) {
			model.getCurrentPlayer().getHand().getCards().remove(chosenCard);

			model.getCardsInPlay().setCard(chosenCard, model.getCurrentTeam(), 
			    model.getCurrentPlayerNumber());
		}
		view.render(model);
	}

	/**
	 * Called by view when trick is over.
	 */
	public void trickOver() {
		JOptionPane.showMessageDialog(view.getFrame(), "Trick over");		
		clearTable();		
	}	
	/**
	 *  Creates new game.
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
	 * clears the table.
	 */
	public void clearTable() {
		this.model.clearTable();
		view.close();
		view = new View(this, model);
		refresh();
	}

	/**
	 * Select trumps and sets up the model.
	 */
	public void selectTrump() {
	  
	  // Save state of players in model
	  Teams startingTeam = model.getCurrentTeam();
	  PlayerNumber startingPlayerNumber = model.getCurrentPlayerNumber();
	  
		boolean isTrumpSelected = false;
		boolean allPlayersPassed = false;
		int playersPassed = 0;
		
		while (!isTrumpSelected && !allPlayersPassed) {
		  
		  // Display option pane
			String[] buttons = {"Take card", "Pass" };
			String displayMessage = model.getCurrentTeam().name() 
			  + " " + model.getCurrentPlayerNumber().name()
				+ ", do you want to take this card? \n\n"
			  + model.getTrumpCard().getCardStringValue();
			int returnValue = JOptionPane.showOptionDialog(view.getFrame(), 
			    displayMessage, "Select trump",
			    JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
			
			// If selected "Take card" then trump has been selected
			isTrumpSelected = (returnValue == 0);
			
			model.nextPlayer();
			playersPassed++; 
			allPlayersPassed = (playersPassed == 4);			
		}
		
		if (!isTrumpSelected) {
		  
		  allPlayersPassed = false;
		  playersPassed = 0;
		  
		  while (!isTrumpSelected && !allPlayersPassed) {
		    
		    String[] buttons = {"Hearts", "Diamonds", "Clubs", "Spades", "Pass" };
		    
	      String displayMessage = model.getCurrentTeam().name() 
	        + " " + model.getCurrentPlayerNumber().name()
	        + ", do you want to select trump?";
	      
	      int returnValue = JOptionPane.showOptionDialog(view.getFrame(), 
	          displayMessage, "Select trump",
	          JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
	      
	      // If selected anything but "pass" then trump has been selected
	      isTrumpSelected = (returnValue != 4);
	      
	      if (isTrumpSelected) {
	        if (returnValue == 0) {
	          model.setTrumpSuit(Suit.HEARTS);
          } else if (returnValue == 1) {
            model.setTrumpSuit(Suit.DIAMONDS);
          } else if (returnValue == 2) {
            model.setTrumpSuit(Suit.CLUBS);
          } else if (returnValue == 3) {
            model.setTrumpSuit(Suit.SPADES);
          }
	      }
	      
	      model.nextPlayer();
	      playersPassed++; 
	      allPlayersPassed = (playersPassed == 3);	      
		  }
		  
		  // "Screw the dealer" case
		  if (allPlayersPassed && !isTrumpSelected) {
		    String[] buttons = {"Hearts", "Diamonds", "Clubs", "Spades" };
        
        String displayMessage = model.getCurrentTeam().name() 
          + " " + model.getCurrentPlayerNumber().name()
          + ", please select trump.";
        
        int returnValue = JOptionPane.showOptionDialog(view.getFrame(), 
            displayMessage, "Select trump",
            JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
        
        if (isTrumpSelected) {
          if (returnValue == 0) {
            model.setTrumpSuit(Suit.HEARTS);
          } else if (returnValue == 1) {
            model.setTrumpSuit(Suit.DIAMONDS);
          } else if (returnValue == 2) {
            model.setTrumpSuit(Suit.CLUBS);
          } else if (returnValue == 3) {
            model.setTrumpSuit(Suit.SPADES);
          }
        }
		  }
		  
		}	
		
		// Reset original starting players
		model.setCurrentTeam(startingTeam);
		model.setCurrentPlayerNumber(startingPlayerNumber);		
	}	
}
