package controller;

/**
 * Main class with entry method.
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 */
final class Start {
  
 /**
  * Private to make this non-instantiable.   
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
