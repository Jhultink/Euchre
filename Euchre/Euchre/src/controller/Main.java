package controller;

import view.View;

import models.GameModel;

public class Main {

    public static void main(String[] args) {

	GameController controller = new GameController();

	controller.start();
	View view = new View();
	GameModel model = new GameModel();
	view.render(model);

    }

}
