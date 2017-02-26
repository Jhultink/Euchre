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
    Assert.assertNotNull(controller);
    Assert.assertNotNull(model);
    
    View testView = new View(controller, model);
    
    testView.render(model);
    Assert.assertTrue(testView.getGameModel() == model);
    
    AboutWindow aw = new AboutWindow();
    Assert.assertNotNull(aw);
    
  }
}
