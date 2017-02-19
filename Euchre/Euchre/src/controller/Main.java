package controller;
/**
 * Main class with entry method.
 *
 */
public class Main {
  /**
   * Main method, entry for program.
   * 
   * @param args
   *          passed in via command line
   */
  public static void main(final String[] args) {

    GameController controller = new GameController();

    controller.start();

  }
}
