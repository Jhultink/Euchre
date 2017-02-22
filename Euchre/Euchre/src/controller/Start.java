package controller;

/**
 * Main class with entry method.
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
