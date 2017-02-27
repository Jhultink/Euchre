package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class DealTest {

  @Test
  public void test() {
    GameDeck deck = new GameDeck();
    Deal deal = deck.deal();
    Hand r1 = deal.getRedOne();
    Hand r2 = deal.getRedTwo();
    Hand b1 = deal.getBlackOne();
    Hand b2 = deal.getBlackTwo();
    
    Card card = deal.getTrump();
    
    assertTrue(r1.getCards().size() == 5);
    assertTrue(r2.getCards().size() == 5);
    assertTrue(b1.getCards().size() == 5);
    assertTrue(b2.getCards().size() == 5);
    
  }

}
