package view;

import org.junit.Assert;
import org.junit.Test;

import models.Card;
import models.CardValue;
import models.Player;
import models.PlayerNumber;
import models.Suit;
import models.Teams;

/**
 * 
 * @author Ryan, Jaredt, Keith
 *
 */
public class CardButtonTest {

  /**
   * 
   * @throws Exception
   */
  @Test
  public static void testGetMethods() throws Exception {
    Card testCard = new Card(CardValue.ACE, Suit.CLUBS);
    Player testPlayer = new Player(Teams.BLACK, PlayerNumber.FIRST);
    CardButton cButton = new CardButton(testCard, testPlayer);

    Assert
        .assertTrue(cButton.getOwner().getHand().getCards().contains(testCard));


    Assert.assertEquals(Suit.CLUBS, cButton.getCard().getCardSuit());
    Assert.assertEquals(CardValue.ACE, cButton.getCard().getCardValue());

    Assert.assertEquals(testCard.getCardStringValue(),
        cButton.getCard().getCardStringValue());

  }
}
