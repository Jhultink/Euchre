package models;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Jaredt
 */
public class CardValueTest {

  /**
   * Test card values.
   */
  @Test
  public void test() {
    assertEquals("9", CardValue.NINE.toString());
    assertEquals("10", CardValue.TEN.toString());
    assertEquals("Jack", CardValue.JACK.toString());
    assertEquals("Queen", CardValue.QUEEN.toString());
    assertEquals("King", CardValue.KING.toString());
    assertEquals("Ace", CardValue.ACE.toString());
  }

}
