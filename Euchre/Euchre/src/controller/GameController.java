package controller;

import view.View;
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
		view = new View();
    }

    /**
     * 
     */
    public void start() {

		model.newHand(Teams.RED, PlayerNumber.FIRST);
		view.render(model);

    }

}
