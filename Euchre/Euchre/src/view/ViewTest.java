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
   * Test gui .
   */
  @Test
  public void testGuiComponents() {
    GameController controller = new GameController();
    Assert.assertNotNull(controller);
    
    GameModel model = new GameModel();
    Assert.assertNotNull(model);
    
    View testView = new View(controller, model);
    Assert.assertNotNull(testView);
    
    AboutWindow aw = new AboutWindow();
    Assert.assertNotNull(aw);
    
  }
}
