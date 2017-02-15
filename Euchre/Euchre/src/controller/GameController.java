package controller;

import view.View;
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
     * .
     */
    public void start() {

	model.newHand(Teams.RED, PlayerNumber.FIRST);
	view.render(model);

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

	    model.cardsInPlay.setCard(chosenCard, 
		    model.getCurrentTeam(), 
		    model.getCurrentPlayerNumber());
	}
	view.render(model);
    }

    /**
     * 
     */
    public void newGame() {
	this.model = new GameModel();
	this.view = new View(this, model);
	this.start();
    }

}
