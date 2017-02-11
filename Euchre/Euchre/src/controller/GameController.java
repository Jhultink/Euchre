package controller;

import view.View;
import models.GameModel;
import models.PlayerNumber;
import models.Teams;

public class GameController {

    GameModel model;
    View view;

    GameController() {

	model = new GameModel();
	view = new View();
    }

    public void start() {

	model.newHand(Teams.RED, PlayerNumber.FIRST);
	view.render(model);

    }

}
