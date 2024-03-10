package no.ntnu.idatx2003.oblig3.cardgame;

import com.sun.javafx.geom.Rectangle;
import java.util.Collection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public  class MainWindow extends Application {
  private DeckOfCards deck;
  private HandOfCards hand;

  private int numberOfCardsToDeal;


  @Override
  public void start(Stage stage) throws Exception {
    deck = new DeckOfCards();
    stage.setTitle("Card Game App");
    BorderPane rootNode = new BorderPane();
    Scene scene = new Scene(rootNode, 600, 600);








    // VBox to hold buttons for dealing and checking hand of cards
    VBox buttonBox = new VBox(10); // 10 px are added between each button
    buttonBox.setPadding(new Insets(30, 30, 30, 30));

    // Button for dealing a hand of cards
    Button dealHandButton = new Button("Deal Hand");
    dealHandButton.setPrefSize(100, 20);
    buttonBox.getChildren().add(dealHandButton);
    // Label for displaying dealt cards.

    Label dealtCardsLabel = new Label("Dealt cards will be displayed here");
    dealtCardsLabel.setPrefSize(400, 150);
    buttonBox.getChildren().add(dealtCardsLabel);


    dealHandButton.setOnAction(e -> {
        Collection<PlayingCard> cards = deck.dealHand(5);
        StringBuilder cardsAsString = new StringBuilder("Dealt cards: ");
        hand = new HandOfCards(cards);
        for (PlayingCard card : cards) {
            dealtCardsLabel.setText(cardsAsString.append(card.getAsString()).append("   ").toString());
        }
    });

    // Button for checking hand of cards
    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setPrefSize(100, 20);
    buttonBox.getChildren().add(checkHandButton);


    checkHandButton.setOnAction(e -> {
      if (hand != null && hand.isFlush()) {
        dealtCardsLabel.setText("Flush");
      } else {
        dealtCardsLabel.setText("Not a flush");
      }
    });




    // Adds the buttonBox to the right side of the BorderPane.
    BorderPane.setAlignment(buttonBox, Pos.CENTER_RIGHT);
    rootNode.setRight(buttonBox);
    // Adds the dealtCardsLabel to the left side of the BorderPane.
    BorderPane.setAlignment(dealtCardsLabel, Pos.TOP_LEFT);
    rootNode.setLeft(dealtCardsLabel);



    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
