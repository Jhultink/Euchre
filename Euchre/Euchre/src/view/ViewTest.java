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
   * Test gui 
   */
  @Test
  public void testGuiComponents() {
    GameController controller = new GameController();
    GameModel model = new GameModel();
    Assert.assertNotNull(controller);
    Assert.assertNotNull(model);
    
    View testView = new View(controller, model);
  
    
    AboutWindow aw = new AboutWindow();
    Assert.assertNotNull(aw);
    
  }
}
