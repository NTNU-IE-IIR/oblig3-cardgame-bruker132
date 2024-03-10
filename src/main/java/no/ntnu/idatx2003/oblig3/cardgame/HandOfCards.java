package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collection;


public class HandOfCards {
  private Collection<PlayingCard> hand;


  public HandOfCards(Collection<PlayingCard> hand) {
  }

    public HandOfCards() {
    this.hand = hand;
  }

  public int culculateSumOfCards() {
    int sum = 0;
    for (PlayingCard card : hand) {
      sum += card.getFace();
    }
    return sum;
  }

  public Collection<PlayingCard> getHearts() {
    Collection<PlayingCard> hearts = new ArrayList<>();
    for (PlayingCard card : hand) {
      if (card.getSuit() == 'H') {
        hearts.add(card);
        }
      }
    return hearts;
  }

  public boolean isFlush() {
    char suit = this.hand.iterator().next().getSuit();
    for (PlayingCard card : this.hand) {
      if (card.getSuit() != suit) {
        return false;
      }
    }
    return true;
  }

 public boolean checkIfHasQueenOfSpades() {
    for (PlayingCard card : hand) {
      if (card.getSuit() == 'S' && card.getFace() == 12) {
        return true;
      }
    }
    return false;
  }
}
