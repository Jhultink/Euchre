package controller;

import view.View;
import javax.swing.JOptionPane;

import models.Card;
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
  GameController() {

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
   * Checks if played card is valid and puts it in play if it is.
   * 
   * @param chosenCard
   *          card to check
   * @param player
   *          player who played card
   */
  public void playCard(final Card chosenCard, final Player player) {
    model.getPlayer(player.getTeam(), player.getPlayerPosition()).getHand()
        .getCards().remove(chosenCard);

    model.getCardsInPlay().setCard(chosenCard, model.getCurrentTeam(),
        model.getCurrentPlayerNumber());

    model.nextPlayer();
    view.render(model);
  }

  /**
   * Called by view when trick is over.
   */
  public void trickOver() {

    for (Card card : model.getCardsInPlay().getAllCards()) {
      Card c = card;
    }

    JOptionPane.showMessageDialog(view.getFrame(), "Trick over");
    clearTable();

    if (model.isHandOver()) {
      handOver();
    }
  }

  /**
   * Called when a hand is over.
   */
  public void handOver() {
    JOptionPane.showMessageDialog(view.getFrame(), "Hand over");
  }

  /**
   * Creates new game.
   */
  public void newGame() {
    view.close();
    this.model = new GameModel();
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
    // view.close();
    // view = new View(this, model);
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
    PlayerNumber playerWhoChoseTrump = null;

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
        playerWhoChoseTrump = model.getCurrentPlayerNumber();
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

          playerWhoChoseTrump = model.getCurrentPlayerNumber();
          teamWhoChoseTrump = model.getCurrentTeam();

        }

        model.nextPlayer();
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
