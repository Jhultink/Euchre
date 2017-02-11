package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.GameModel;
import models.PlayerNumber;
import models.Teams;
import view.View;

public class TestUI {

	View view;
	
	@Before
	public void setupView(){
		view = new View();
	}
	
	@Test
	public void test() {
		GameModel model = new GameModel();		
		model.newHand(Teams.RED, PlayerNumber.FIRST);
		
		view.render(model);

	}
}
