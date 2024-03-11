package no.ntnu.idatx2003.oblig3.cardgame;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Collection;

public class MainWindow extends Application {
  private DeckOfCards deck;
  private HandOfCards hand;

  @Override
  public void start(Stage stage) throws Exception {
    deck = new DeckOfCards();
    stage.setTitle("Card Game App");
    BorderPane rootNode = new BorderPane();
    Scene scene = new Scene(rootNode, 800, 600);

    // ButtonBox on the right side
    VBox buttonBox = new VBox(10);
    buttonBox.setPadding(new Insets(30, 30, 30, 30));

    // Buttons for dealing and checking hand of cards
    Button dealHandButton = new Button("Deal Hand");
    dealHandButton.setPrefSize(100, 20);

    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setPrefSize(100, 20);

    // Label for displaying dealt cards.
    TextFlow dealtCardsFlow = new TextFlow();
    Label dealtCardsLabel = new Label("Dealt cards will be displayed here");
    dealtCardsLabel.setPrefSize(120, 100);

    // Label for Flush on the left
    Label flushLabel = new Label("Flush");
    flushLabel.setPrefSize(300, 100);

    dealHandButton.setOnAction(e -> {
      Collection<PlayingCard> cards = deck.dealHand(5);
      hand = new HandOfCards(cards);
      dealtCardsFlow.getChildren().clear();
      dealtCardsFlow.getChildren().add(new Text("Dealt cards: "));
      for (PlayingCard card : cards) {
        dealtCardsFlow.getChildren().add(new Text(card.getAsString() + "   "));
      }
    });

    checkHandButton.setOnAction(e -> {
      if (hand != null && hand.isFlush()) {
        flushLabel.setText("Flush: Yes");
      } else {
        flushLabel.setText("Flush: No");
      }
    });

    // Adds the buttons to the buttonBox
    buttonBox.getChildren().addAll(dealHandButton, checkHandButton);

    // Adds the buttonBox to the right side of the BorderPane.
    rootNode.setRight(buttonBox);

    // Adds the flushLabel to the left side of the BorderPane.
    BorderPane.setAlignment(flushLabel, Pos.BOTTOM_LEFT);
    BorderPane.setMargin(flushLabel, new Insets(0, 50, 50, 50)); // Adding margin to move it down
    rootNode.setLeft(flushLabel);

    // Center area for displaying dealt cards and buttons
    HBox centerBox = new HBox(10);
    centerBox.setAlignment(Pos.CENTER);

    // Adds the dealtCardsFlow to the centerBox
    centerBox.getChildren().add(dealtCardsFlow);

    // Adds the centerBox to the center of the BorderPane.

    rootNode.setCenter(centerBox);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
