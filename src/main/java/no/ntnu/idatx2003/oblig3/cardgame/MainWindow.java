package no.ntnu.idatx2003.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainWindow extends Application {

  private DeckOfCards deck;
  private HandOfCards hand;
  private ImageView cardBackImageView;

  @Override
  public void start(Stage stage) throws Exception {
    FlowPane imageCardsBox = new FlowPane();
    imageCardsBox.setMaxWidth(400 * 5);
    imageCardsBox.setAlignment(Pos.CENTER);

    deck = new DeckOfCards();
    stage.setTitle("Card Game App");
    BorderPane rootNode = new BorderPane();
    Scene scene = new Scene(rootNode, 800, 600);

    // ButtonBox on the right side
    VBox userBox = new VBox(10);
    userBox.setPadding(new Insets(30, 30, 30, 30));

    // Buttons for dealing and checking hand of cards
    Button dealHandButton = new Button("Deal Hand");
    dealHandButton.setPrefSize(100, 20);

    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setPrefSize(100, 20);

    // Label for displaying dealt cards.
    TextFlow dealtCardsFlow = new TextFlow();
    Label dealtCardsLabel = new Label("Dealt cards will be displayed here");
    dealtCardsLabel.setPrefSize(120, 100);
    dealtCardsLabel.setWrapText(true);

    // Load the image for the back of the card
    Image cardBackImage = new Image(getClass().getResource("/cards/C1.png").toExternalForm());
    cardBackImageView = new ImageView(cardBackImage);
    cardBackImageView.setFitWidth(100); // Set width to fit your card size
    cardBackImageView.setPreserveRatio(true);

    // Label for Flush on the left
    Label flushLabel = new Label("Flush");
    flushLabel.setPrefSize(50, 50);

    dealHandButton.setOnAction(e -> {
      imageCardsBox.getChildren().clear();
      Collection<PlayingCard> cards = deck.dealHand(5);
      hand = new HandOfCards(cards);
      dealtCardsFlow.getChildren().clear();
      dealtCardsFlow.getChildren().add(new Text("Dealt cards: "));
      List<ImageView> cardImageViews = getImagesFromHand(cards);
      imageCardsBox.getChildren().addAll(cardImageViews);
    });

    checkHandButton.setOnAction(e -> {
      if (hand != null && hand.isFlush()) {
        flushLabel.setText("Flush: Yes");
      } else {
        flushLabel.setText("Flush: No");
      }
    });

    // Adds the buttons to the buttonBox
    userBox.getChildren().addAll(dealHandButton, checkHandButton);
    rootNode.setRight(userBox);

    // Adds the flushLabel to the left side of the BorderPane.
    BorderPane.setAlignment(flushLabel, Pos.BOTTOM_LEFT);
    BorderPane.setMargin(flushLabel, new Insets(0, 10, 0, 10)); // Adding margin to move it down
    rootNode.setLeft(flushLabel);

    BorderPane.setAlignment(dealtCardsFlow, Pos.CENTER);
    BorderPane.setMargin(dealtCardsFlow, new Insets(0, 50, 50, 50)); // Adding margin to move it down
    rootNode.setCenter(imageCardsBox);


    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public List<ImageView> getImagesFromHand(Collection<PlayingCard> cards) {
    List<ImageView> cardImageViews = new ArrayList<>();
    cardImageViews.clear();
    for (PlayingCard card : cards) {
      String imagePath = "/cards/" + card.getAsString() + ".png"; // Assuming card names match image filenames
      Image cardImage = new Image(getClass().getResource(imagePath).toExternalForm());
      ImageView cardImageView = new ImageView(cardImage);
      cardImageView.setFitWidth(100); // Set width to fit your card size
      cardImageView.setPreserveRatio(true);
      cardImageViews.add(cardImageView);
    }
    return cardImageViews;
  }
}
