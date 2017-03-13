package models;


import org.junit.Assert;
import org.junit.Test;


/**
 * Test class for cards.
 * @author Jaredt, Keith, Ryan
 *
 */
public class CardsTest {

  /**
   * Test case.
   */
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
  /**
   * Test card values.
   */
	public void testGetCardValue() {
		Card card9 = new Card(CardValue.NINE, Suit.CLUBS);
		int val9 = card9.getCardIntValue();
		Assert.assertEquals(9, val9);
	
		Card card10 = new Card(CardValue.TEN, Suit.CLUBS);
    int val10 = card10.getCardIntValue();
    Assert.assertEquals(10, val10);
    
    Card cardJ = new Card(CardValue.JACK, Suit.CLUBS);
    int valJ = cardJ.getCardIntValue();
    Assert.assertEquals(11, valJ);
    
    Card cardQ = new Card(CardValue.QUEEN, Suit.CLUBS);
    int valQ = cardQ.getCardIntValue();
    Assert.assertEquals(12, valQ);
    
    Card cardK = new Card(CardValue.KING, Suit.CLUBS);
    int valK = cardK.getCardIntValue();
    Assert.assertEquals(13, valK);
    
    Card cardA = new Card(CardValue.ACE, Suit.CLUBS);
    int valA = cardA.getCardIntValue();
    Assert.assertEquals(14, valA);   
	}
	
	/**
   * Test case.
   */
	@Test
	public void testGetters() {
	  Card card = new Card(CardValue.ACE, Suit.CLUBS);
	  Assert.assertEquals(Suit.CLUBS, card.getCardSuit());
	  Assert.assertEquals(CardValue.ACE, card.getCardValue());
	}
	
	/**
   * Test case.
   */
	@Test
  public void testStringValues() {
	  Card card = new Card(CardValue.ACE, Suit.CLUBS);
	  Assert.assertEquals("Ace of Clubs", card.getCardStringValue());
	}
	
	/**
   * Test case.
   */
	@Test
	public void testEquality() {
	  Card card = new Card(CardValue.ACE, Suit.CLUBS);
	  Card card2 = new Card(CardValue.KING, Suit.CLUBS);
	  Card card3 = new Card(CardValue.KING, Suit.CLUBS);
	  Card card4 = new Card(CardValue.KING, Suit.DIAMONDS);
    Assert.assertFalse(card.equals(card2));
    Assert.assertFalse(card3.equals(card4));
    Assert.assertFalse(card.equals(null));
    Assert.assertTrue(card2.equals(card3));
    Assert.assertFalse(card.equals(card3));
    Assert.assertEquals(card.hashCode(), 
        14 * card.getCardSuit().hashCode());
	}
	/**
   * Test case.
   */
  @Test
  public void testTrump() {
    
    Card card = new Card(CardValue.ACE, Suit.CLUBS);
    Card card2 = new Card(CardValue.KING, Suit.HEARTS);
    
    Card card3 = new Card(CardValue.JACK, Suit.CLUBS);
    Card card4 = new Card(CardValue.JACK, Suit.DIAMONDS);
    Card card5 = new Card(CardValue.JACK, Suit.SPADES);
    Card card6 = new Card(CardValue.JACK, Suit.HEARTS);

    Assert.assertTrue(card.isTrump(Suit.CLUBS));
    
    Assert.assertTrue(card3.isTrump(Suit.CLUBS));
    Assert.assertTrue(card3.isTrump(Suit.SPADES));
    
    Assert.assertTrue(card4.isTrump(Suit.DIAMONDS));
    Assert.assertTrue(card4.isTrump(Suit.HEARTS));
    
    Assert.assertTrue(card5.isTrump(Suit.CLUBS));
    Assert.assertTrue(card5.isTrump(Suit.SPADES));
    
    Assert.assertTrue(card6.isTrump(Suit.DIAMONDS));
    Assert.assertTrue(card6.isTrump(Suit.HEARTS));
    
    Assert.assertFalse(card2.isTrump(Suit.CLUBS));
    
    Assert.assertFalse(card3.isTrump(Suit.DIAMONDS));
    Assert.assertFalse(card4.isTrump(Suit.CLUBS));
    Assert.assertFalse(card5.isTrump(Suit.HEARTS));
    Assert.assertFalse(card6.isTrump(Suit.SPADES));
    
  }
}




