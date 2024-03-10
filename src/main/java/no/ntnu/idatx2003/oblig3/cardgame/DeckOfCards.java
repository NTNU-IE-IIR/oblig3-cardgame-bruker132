package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

  private final char[] suits = { 'S', 'H', 'D', 'C' };
  private int numberOfCards = 52;
  private int minValue = 1;
  private int maxValue = 13;
  private ArrayList<PlayingCard> cards;

  public DeckOfCards() {
    cards = new ArrayList<>();
    initializeDeck();
  }

  private void initializeDeck() {
    for (char suit : suits) {
      for (int value = minValue; value <= maxValue ; value++) {
        PlayingCard card = new PlayingCard(suit, value);
        cards.add(card);
      }
    }
  }

  public Collection<PlayingCard> dealHand(int amountOfCards) {
    if (amountOfCards < 1 || amountOfCards > this.numberOfCards) {
      throw new IllegalArgumentException("Invalid amount of cards");
    }

    ArrayList<PlayingCard> CardsToBeDealt = new ArrayList<>();
    for (int i = 0; i < amountOfCards; i++) {

      Random random = new Random();
      int randomIndex = random.nextInt(cards.size());
        PlayingCard card = cards.remove(randomIndex);
        CardsToBeDealt.add(card);
    }
    return CardsToBeDealt;
  }

  public int getNumberOfCards() {
    return this.numberOfCards;
  }

}
