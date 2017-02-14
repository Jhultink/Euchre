package controller;

import view.View;
import models.Card;
import models.GameModel;
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

		model = new GameModel();
		view = new View(this);
    }

    /**
     * 
     */
    public void start() {

		model.newHand(Teams.RED, PlayerNumber.FIRST);
		view.render(model);

    }
    
    public void playCard(Card chosenCard) {
		if(model.isValidPlay(chosenCard)) {
			model.getCurrentPlayer().getHand().getCards().remove(chosenCard);
			model.cardsInPlay.setBlackTwoCard(chosenCard);
		}
		view.render(model);
    }

}
