package controller;

import view.View;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import ai.AI;
import ai.AIDifficulty;
import models.Card;
import models.CardValue;
import models.GameModel;
import models.Player;
import models.PlayerNumber;
import models.Suit;
import models.Teams;

/**
 * 
 * @author Jaredt Hultink, Ryan Jones, Keith Rodgers
 *
 */
public class GameController {

  /**
   * Model that holds all the data about the game.
   */
  private GameModel model;

  /**
   * View class that renders the model.
   */
  private View view;

  /**
   * Sets up a new model and view.
   */
  public GameController() {
    this.model = new GameModel();
    model.newHand(Teams.BLACK, PlayerNumber.FIRST);
    this.view = new View(this, model);
  }

  /**
   * Starts the game with Red One.
   */
  public void start() {
    view.render(model);
    selectTrump();
  }
  
  /**
   * Loads new game from the passed game model.
   * @param newModel model to load
   */
  public void loadGame(final GameModel newModel) {
    this.model = newModel;
    this.view.close();
    this.view = new View(this, model);   
    playAI();
    this.view.render(model);
  }

  
  /**
   * Plays AI cards until it hits a non-AI player.
   */
  public void playAI() {
    while (model.getCurrentPlayer().isAI() 
        && !model.isTrickOver()) {
      
      Card aiPlay = AI.getPlay(model, 
          model.getCurrentPlayer(), AIDifficulty.EASY);
      
      model.getCurrentPlayer().getHand().getCards().remove(aiPlay);
      
      model.getCardsInPlay().setCard(aiPlay, model.getCurrentTeam(),
          model.getCurrentPlayerNumber());
      
      model.nextPlayer();    
      view.render(model);
    }
    
  }
  
  /**
   * Plays card, whether or not it is valid.
   * 
   * @param chosenCard
   *          card to check
   * @param player
   *          player who played card
   */
  public void playCard(final Card chosenCard, final Player player) {
            
    model.getPlayer(player.getTeam(), player.getPlayerPosition()).getHand()
      .getCards().remove(chosenCard);

    model.getCardsInPlay().setCard(chosenCard, player.getTeam(),
        player.getPlayerPosition());
    
    model.nextPlayer();    
    view.render(model);
    
    if (!model.isTrickOver()) {
      playAI();
    }
    
  }

  /**
   * Called by view when trick is over.
   */
  public void trickOver() {

    ArrayList<Card> trumpCards = new ArrayList<>();
    ArrayList<Card> leadSuitCards = new ArrayList<>();
    
    // Add cards to either trumpCards or leadSuit since those
    // are the only cases a player can win with
    for (Card card : model.getCardsInPlay().getAllCards()) {
      if (card.isTrump(model.getTrumpSuit())) {
        trumpCards.add(card);
      } else if (card.getCardSuit() == model.getCardsInPlay()
          .getFirstPlayedCard().getCardSuit()) {
        leadSuitCards.add(card);
      }
    }
    
    Card winningCard = null;
    
    // find winning card
    if (!trumpCards.isEmpty()) { // If there is trump, trump will win
      if (trumpCards.contains(new Card(CardValue.JACK, model.getTrumpSuit()))) {
        // right bower
        winningCard = new Card(CardValue.JACK, model.getTrumpSuit());
        
      } else if (trumpCards.contains(new Card(CardValue.JACK, 
          model.getTrumpSuit().oppositeSuit()))) {
        // left bower
        winningCard = new Card(CardValue.JACK, 
            model.getTrumpSuit().oppositeSuit());        
      } else {
        // sort by int representation and pull top one
        // guaranteed to not have bowers, so a normal sort will work
        Collections.sort(trumpCards);
        winningCard = trumpCards.get(0);
      }
      
    } else { // If no trump, lead suit will win
      // sort by int representation and pull top one
      Collections.sort(leadSuitCards);
      winningCard = leadSuitCards.get(0);
    }
    
    Teams winningTeam = model.getCardsInPlay().getTeamOf(winningCard);
    PlayerNumber winningPlayerNumber = model.getCardsInPlay()
        .getPlayerNumberOf(winningCard);
    
    if (winningTeam == Teams.BLACK) {
      model.increaseBlackHandScore();
    } else if (winningTeam == Teams.RED) {
      model.increaseRedHandScore();
    } else {
      System.err.println("Winning team is null");
    }
    
    JOptionPane.showMessageDialog(view.getFrame(), "Winning team: " 
        + winningTeam.name() + " " + winningPlayerNumber.name());
    
    this.clearTable();
    
    model.setCurrentPlayerNumber(winningPlayerNumber);
    model.setCurrentTeam(winningTeam);
    
    model.setStartingTeam(winningTeam);
    model.setStartingPlayerNumber(winningPlayerNumber);
    
    view.render(model);
    
    if (model.isHandOver()) {
      handOver(winningTeam, winningPlayerNumber);
    }
    
    playAI();
  }

  /**
   * Called when a hand is over.
   * @param winningTeam winning team
   * @param winningPlayerNumber winning player number
   */
  public void handOver(final Teams winningTeam, 
      final PlayerNumber winningPlayerNumber) {
        
      JOptionPane.showMessageDialog(view.getFrame(), "Hand over. Black: "
          + model.getBlackHandScore() + " Red: " + model.getRedHandScore());
      
      // If red won hand
      if (model.getRedHandScore() > model.getBlackHandScore()) {
        
        // Won every trick, 4 points
        if (model.getRedHandScore() == 5) {
          model.addToRedScore(4);
        } else if (model.getTeamWhoCalledTrump() == Teams.RED) { 
          // Red called trump, one point
          model.addToRedScore(1);
        } else { // Euchred black
          model.addToRedScore(2);
        }
        
      } else { // Else black won
        
        if (model.getBlackHandScore() == 5) { // Won all 4 tricks
          model.addToBlackScore(4); 
        } else if (model.getTeamWhoCalledTrump() == Teams.BLACK) { 
          // Black called trump
          model.addToBlackScore(1);
        } else { // Euchred red
          model.addToBlackScore(2);
        }        
      }
      
      model.newHand(winningTeam, winningPlayerNumber);
      refresh();
      
      selectTrump();
      
      playAI();
      refresh();
  }

  /**
   * Creates new game.
   */
  public void newGame() {
    view.close();
    this.model = new GameModel();
    this.model.newHand(Teams.BLACK, PlayerNumber.FIRST);
    this.view = new View(this, model);
    this.start();
  }

  /**
   * Re-renders the model.
   */
  public void refresh() {
    view.render(model);
  }

  /**
   * clears the table.
   */
  public void clearTable() {
    this.model.clearTable();
    refresh();
  }

  /**
   * Select trumps and sets up the model.
   */
  public void selectTrump() {

    // Save state of players in model
    Teams startingTeam = model.getCurrentTeam();
    PlayerNumber startingPlayerNumber = model.getCurrentPlayerNumber();

    Teams teamWhoChoseTrump = null;
    
    boolean isTrumpSelected = false;
    boolean allPlayersPassed = false;
    int playersPassed = 0;

    while (!isTrumpSelected && !allPlayersPassed) {

      // Display option pane
      String[] buttons = {"Take card", "Pass" };
      String displayMessage = model.getCurrentTeam().name() + " "
          + model.getCurrentPlayerNumber().name()
          + ", do you want to take this card? \n\n"
          + model.getTrumpCard().getCardStringValue();
      int returnValue = JOptionPane.showOptionDialog(view.getFrame(),
          displayMessage, "Select trump", JOptionPane.PLAIN_MESSAGE, 0, null,
          buttons, buttons[0]);

      // If selected "Take card" then trump has been selected
      if (returnValue == 0) {
        isTrumpSelected = true;
        teamWhoChoseTrump = model.getCurrentTeam();

        model.getCurrentPlayer().getHand().add(model.getTrumpCard());

        view.render(model);

        // Create array of strings for display
        String[] cards = new String[model.getCurrentPlayer().getHand()
            .getCards().size()];
        int i = 0;
        for (Card card : model.getCurrentPlayer().getHand().getCards()) {
          cards[i] = card.getCardStringValue();
          i++;
        }

        int cardIndex = JOptionPane.showOptionDialog(view.getFrame(),
            "Choose a card to discard", "Discard", JOptionPane.PLAIN_MESSAGE, 0,
            null, cards, cards[0]);

        model.getCurrentPlayer().getHand().removeAt(cardIndex);

      }

      model.nextPlayer();
      refresh();

      playersPassed++;
      allPlayersPassed = (playersPassed == 4);
    }

    if (!isTrumpSelected) {

      allPlayersPassed = false;
      playersPassed = 0;

      while (!isTrumpSelected && !allPlayersPassed) {

        String[] buttons = {"Hearts", "Diamonds", "Clubs", "Spades", "Pass" };

        String displayMessage = model.getCurrentTeam().name() + " "
            + model.getCurrentPlayerNumber().name()
            + ", do you want to select trump?";

        int returnValue = JOptionPane.showOptionDialog(view.getFrame(),
            displayMessage, "Select trump", JOptionPane.PLAIN_MESSAGE, 0, null,
            buttons, buttons[0]);

        // If selected anything but "pass" then trump has been selected
        isTrumpSelected = (returnValue != 4);

        if (isTrumpSelected) {
          if (returnValue == 0) {
            model.setTrumpSuit(Suit.HEARTS);
          } else if (returnValue == 1) {
            model.setTrumpSuit(Suit.DIAMONDS);
          } else if (returnValue == 2) {
            model.setTrumpSuit(Suit.CLUBS);
          } else if (returnValue == 3) {
            model.setTrumpSuit(Suit.SPADES);
          }

          teamWhoChoseTrump = model.getCurrentTeam();

        }

        model.nextPlayer();
        refresh();
        playersPassed++;
        allPlayersPassed = (playersPassed == 3);
      }

      // "Screw the dealer" case
      if (allPlayersPassed && !isTrumpSelected) {
        String[] buttons = {"Hearts", "Diamonds", "Clubs", "Spades" };

        String displayMessage = model.getCurrentTeam().name() + " "
            + model.getCurrentPlayerNumber().name() + ", please select trump.";

        int returnValue = JOptionPane.showOptionDialog(view.getFrame(),
            displayMessage, "Select trump", JOptionPane.PLAIN_MESSAGE, 0, null,
            buttons, buttons[0]);

        if (returnValue == 0) {
          model.setTrumpSuit(Suit.HEARTS);
        } else if (returnValue == 1) {
          model.setTrumpSuit(Suit.DIAMONDS);
        } else if (returnValue == 2) {
          model.setTrumpSuit(Suit.CLUBS);
        } else if (returnValue == 3) {
          model.setTrumpSuit(Suit.SPADES);
        }

        teamWhoChoseTrump = model.getCurrentTeam();

      }
    }

    model.setTeamWhoCalledTrump(teamWhoChoseTrump);

    // Reset original starting players
    model.setCurrentTeam(startingTeam);
    model.setCurrentPlayerNumber(startingPlayerNumber);

    view.render(model);
  }
}
