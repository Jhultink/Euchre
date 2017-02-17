package view;

import javax.swing.JFrame;

/**
 * Strategies window is opened up by view to display potential play strategies
 * to the user.
 * 
 * @author Ryan Jones, Jaredt Hultink, Keith Rodgers
 *
 */
public class AboutWindow {

  /**
   * 
   */
  private JFrame frame;

  /**
   * 
   */
  AboutWindow() {
    frame = new JFrame("About the Game");
    frame.setSize(300, 300);
  }

  /**
   * 
   */
  public void render() {
    frame.setVisible(true);
  }
}
