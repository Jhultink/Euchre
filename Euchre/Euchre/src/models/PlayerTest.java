package models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Test;
/**
 * 
 * @author Jaredt
 *
 */
public class PlayerTest implements Serializable {
  
  /**
   * Test players.
   */
  @Test
  public void test() {
    Player p1 = new Player(Teams.RED, PlayerNumber.FIRST);
    Player p2 = new Player(Teams.RED, PlayerNumber.FIRST);
    Player p3 = new Player(Teams.BLACK, PlayerNumber.FIRST);
    Player p4 = new Player(Teams.RED, PlayerNumber.SECOND);
    
    assertTrue(p1.equals(p2));
    assertFalse(p1.equals(null));
    assertFalse(p1.equals(new Object()));
    assertFalse(p1.equals(p3));
    assertFalse(p1.equals(p4));
      
    p4.setPlayerPosition(PlayerNumber.FIRST);
    assertTrue(p4.getPlayerPosition() == PlayerNumber.FIRST);
  }

}
