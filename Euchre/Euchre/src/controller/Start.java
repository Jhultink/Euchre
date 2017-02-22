package controller;

/**
 * Main class with entry method.
 * 
<<<<<<< HEAD
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
=======
 * @author Jaredt Hultink, Keith Rodgers, Ryan Jones
>>>>>>> branch 'master' of https://github.com/Jhultink/Euchre.git
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
