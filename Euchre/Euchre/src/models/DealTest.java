package models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for game deck class.
 * 
 * @author Jaredt, Ryan, Keith
 *
 */
public class DealTest {

  /**
   * Test game deck methods.
   */
  @Test
  public void test() {
    GameDeck deck = new GameDeck();
    Deal deal = deck.deal();
    Hand r1 = deal.getRedOne();
    Hand r2 = deal.getRedTwo();
    Hand b1 = deal.getBlackOne();
    Hand b2 = deal.getBlackTwo();

    Card card = deal.getTrump();
    Assert.assertTrue(card.getCardSuit() == deal.getTrump().getCardSuit());

    Assert.assertTrue(r1.getCards().size() == 5);
    Assert.assertTrue(r2.getCards().size() == 5);
    Assert.assertTrue(b1.getCards().size() == 5);
    Assert.assertTrue(b2.getCards().size() == 5);

  }

}
