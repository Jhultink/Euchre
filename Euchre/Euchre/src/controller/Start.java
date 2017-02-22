package controller;

/**
 * Main class with entry method.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 */
final class Start {
  
  /**
   * To prevent instantiation and to make checkstyle happy.
   */
  private Start() {
    
  }
  
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
