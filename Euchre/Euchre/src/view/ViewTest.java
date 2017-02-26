package view;

import org.junit.Assert;
import org.junit.Test;

import controller.GameController;
import models.GameModel;

/**
 * Test functions of View Package classes.
 * 
 * @author Ryan Jones, Jaredt Hultink, Keith Rodgers
 *
 */
public class ViewTest {  
  /**
   * 
   * @throws Exception - exception thrown
   */
  @Test
  public static void testGuiComponents() throws Exception {
    GameController controller = new GameController();
    GameModel model = new GameModel();
    View testView = new View(controller, model);
    
    Assert.assertTrue(testView != null);
    
    testView.render(model);
    Assert.assertTrue(testView.getGameModel() != null);
    
    AboutWindow aw = new AboutWindow();
    Assert.assertTrue(aw != null);
  }
}
