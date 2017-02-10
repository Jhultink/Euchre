package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import models.GameModel;
import models.PlayerNumber;
import models.Teams;
import view.View;

public class TestUI {

	@Test
	public void test() {
		GameModel model = new GameModel();		
		model.newHand(Teams.RED, PlayerNumber.FIRST);
		
		View.render(model);
	}

}
