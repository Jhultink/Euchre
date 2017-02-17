package view;

import javax.swing.JFrame;

/**
 * Strategies window is opened up by view to display potential play strategies
 * to the user.
 * 
 * @author Ryan Jones, Jaredt Hultink, Keith Rodgers
 *
 */
public class StrategiesWindow {

  /**
   * 
   */
  private JFrame frame;

  /**
   * 
   */
  StrategiesWindow() {
    frame = new JFrame("Pro Strats");
    frame.setSize(300, 300);
  }

  /**
   * 
   */
  public void render() {
    frame.setVisible(true);
  }
}
