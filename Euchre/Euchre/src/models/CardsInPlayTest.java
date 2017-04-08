package models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test CardsInPlay.
 *
 */
public class CardsInPlayTest  {

  /**
   * Test case.
   */
  @Test
  public void testGetCardValue() {
    Card card = new Card(CardValue.TEN, Suit.CLUBS);
    
    CardsInPlay cip = new CardsInPlay();
    
    Assert.assertTrue(cip.isEmpty());
    
    cip.setRedOneCard(card);
    Assert.assertFalse(cip.isEmpty());
    
    Assert.assertEquals(card, cip.getFirstPlayedCard());

    cip.setRedTwoCard(card);
    Assert.assertFalse(cip.isEmpty());

    cip.setBlackOneCard(card);
    Assert.assertFalse(cip.isEmpty());

    cip.setBlackTwoCard(card);
    Assert.assertFalse(cip.isEmpty());
    
    Assert.assertEquals(cip.getRedOneCard(), card);
    Assert.assertEquals(cip.getRedTwoCard(), card);
    Assert.assertEquals(cip.getBlackOneCard(), card);
    Assert.assertEquals(cip.getBlackTwoCard(), card);
    
    cip.clear();
    cip.setRedOneCard(card);
    Assert.assertEquals(cip.getRedOneCard(), card);
    
    cip.clear();
    cip.setRedTwoCard(card);
    Assert.assertEquals(cip.getRedTwoCard(), card);
    
    cip.clear();
    cip.setBlackOneCard(card);
    Assert.assertEquals(cip.getBlackOneCard(), card);
    
    cip.clear();
    Assert.assertTrue(cip.isEmpty());
    cip.setBlackTwoCard(card);
    Assert.assertEquals(cip.getBlackTwoCard(), card);
  }
  
  /**
   * Test case.
   */
  @Test
  public void testSetPlayerCard() {
    
    Card card = new Card(CardValue.QUEEN, Suit.DIAMONDS);
    CardsInPlay cip = new CardsInPlay();
    
    for (PlayerNumber number : PlayerNumber.values()) {
      for (Teams team : Teams.values()) {
        cip.setCard(card, team, number);
        Assert.assertEquals(card, cip.getCard(team, number));
        Assert.assertEquals(card, cip.getCard(new Player(team, number)));
      }
    }
    
  }
}
