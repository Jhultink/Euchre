package models;

/**
 * @author Jaredt Hultink, Ryan Jones
 *         Keith Rodgers
 *
 * @version 0.1.0
 *         First Version of the program
 */
public class Card {

    /** Integer value of card. */
    private int cardValue;

    /** String value of suit and for the card's value. */
    private String cardSuit, cardStringValue;

    /**
     * Default constructor for the class.
     */
    Card() {
        cardValue = -1;
        cardSuit = "NULL_SUIT";
        cardStringValue = "NULL_CARD_VALUE";
    }

    /**
     * Overloading constructor for the class.
     *
     * @param newCardValue
     *            program-defined card value
     * @param newCardSuit
     *            program-defined value for suit
     **/
    Card(final int newCardValue, final String newCardSuit) {

        // Initialize card value and suit.
        cardValue = newCardValue;
        cardSuit = newCardSuit;
        final int nineValue = 9, tenValue = 10,
                jackValue = 11, queenValue = 12,
                kingValue = 13, aceValue = 14;

        // Set card's string value based on integer value.
        switch (newCardValue) {
        case nineValue:
            cardStringValue = "Nine";
            break;
        case tenValue:
            cardStringValue = "Ten";
            break;
        case jackValue:
            cardStringValue = "Jack";
            break;
        case queenValue:
            cardStringValue = "Queen";
            break;
        case kingValue:
            cardStringValue = "King";
            break;
        case aceValue:
            cardStringValue = "Ace";
            break;
        default:
            cardStringValue = "NULL_CARD_VALUE";
        }
    }

    /**
     * Method that returns the integer value of the card.
     *
     * @return cardValue
     *             Value to return.
     */
    final int getCardValue() {
        return cardValue;
    }

    /**
     * Method that returns the suit of the card.
     *
     * @return cardSuit
     *             String value to return.
     */
    final String getCardSuit() {
        return cardSuit;
    }

    /**
     * Method that returns a string of the integer value of the card.
     *
     * @return cardStringValue
     *             String value to return.
     */
    final String getCardStringValue() {
        return cardStringValue;
    }
}
