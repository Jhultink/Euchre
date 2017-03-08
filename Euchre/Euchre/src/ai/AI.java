package ai;

import models.Card;
import models.GameModel;
import models.Player;

/**
 * Helper class for Plater AI
 */
public class AI {

  /**
   * @param model current model
   * @param player player to get a card to play from
   * @param difficulty AI difficulty
   * @return Card to player from passed player
   */
  public static Card getPlay(GameModel model, Player player, AIDifficulty difficulty) {
      
    // If player has no cards, throw exception
    if(player.getHand().getCards().isEmpty()){
      throw new IllegalArgumentException("Passed player with no cards");
    }
    
    // If player has one card, play tha
    if(player.getHand().getCards().size() == 1) {
      return player.getHand().getCards().get(0);
    }
    
    return null;
    
    
  }
  
}
