package models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test GameModel.
 */
public class GameModelTest {

  /**
   * Test case.
   */
  @Test
  public void testGettersAndSetters() {

    GameModel model = new GameModel();
    model.clearTable();
    model.newHand(Teams.RED, PlayerNumber.FIRST);

    // Player setup
    model.setCurrentPlayer(new Player(Teams.RED, PlayerNumber.FIRST));
    Assert.assertEquals(new Player(Teams.RED, PlayerNumber.FIRST),
        model.getCurrentPlayer());
    model.nextPlayer();

    model.setCurrentPlayer(new Player(Teams.RED, PlayerNumber.SECOND));
    Assert.assertEquals(new Player(Teams.RED, PlayerNumber.SECOND),
        model.getCurrentPlayer());
    model.nextPlayer();

    model.setCurrentPlayer(new Player(Teams.BLACK, PlayerNumber.FIRST));
    Assert.assertEquals(new Player(Teams.BLACK, PlayerNumber.FIRST),
        model.getCurrentPlayer());
    model.nextPlayer();

    model.setCurrentPlayer(new Player(Teams.BLACK, PlayerNumber.SECOND));
    Assert.assertEquals(new Player(Teams.BLACK, PlayerNumber.SECOND),
        model.getCurrentPlayer());

    Assert.assertEquals(PlayerNumber.SECOND, model.getCurrentPlayerNumber());
    Assert.assertEquals(Teams.BLACK, model.getCurrentTeam());

    Assert.assertFalse(model.isHandOver());
    
    model.getPlayer(Teams.BLACK, PlayerNumber.FIRST).getHand().getCards()
        .clear();
    model.getPlayer(Teams.BLACK, PlayerNumber.SECOND).getHand().getCards()
        .clear();
    model.getPlayer(Teams.RED, PlayerNumber.FIRST).getHand().getCards().clear();
    model.getPlayer(Teams.RED, PlayerNumber.SECOND).getHand().getCards()
        .clear();

    Assert.assertTrue(model.isHandOver());

    model.nextPlayer();

    model.addToBlackScore(1);
    model.addToRedScore(1);
    model.increaseBlackHandScore();
    model.increaseRedHandScore();

    model.setTrumpSuit(Suit.DIAMONDS);
    Assert.assertEquals(Suit.DIAMONDS, model.getTrumpSuit());

    model.setTeamWhoCalledTrump(Teams.BLACK);
    Assert.assertEquals(Teams.BLACK, model.getTeamWhoCalledTrump());

    model.setStartingTeam(Teams.BLACK);
    Assert.assertEquals(Teams.BLACK, model.getStartingTeam());

    model.setStartingPlayerNumber(PlayerNumber.FIRST);
    Assert.assertEquals(PlayerNumber.FIRST, model.getStartingPlayerNumber());

  }

}
