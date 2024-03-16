package no.ntnu.idatx2003.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    // VBox for labels on the left side
    VBox labelBox = new VBox(10);
    labelBox.setPadding(new Insets(0, 10, 0, 10));


    // Label for hearts on the left
    Label heartsLabel = new Label("Hearts");
    heartsLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

    // Label for sum of cards on the left
    Label sumLabel = new Label("Sum of cards");
    sumLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

    // Label for Flush on the left
    Label flushLabel = new Label("Flush");
    flushLabel.setPrefSize(50, 50);

    // Label for Queens of Spades
    Label queensOfSpadesLabel = new Label("Queens of Spades");
    queensOfSpadesLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

    labelBox.getChildren().addAll(flushLabel, queensOfSpadesLabel, heartsLabel, sumLabel);
    rootNode.setLeft(labelBox);




    dealHandButton.setOnAction(e -> {
      imageCardsBox.getChildren().clear();
      Collection<PlayingCard> cards = deck.dealHand(5);
      hand = new HandOfCards(cards);
      dealtCardsFlow.getChildren().clear();
      dealtCardsFlow.getChildren().add(new Text("Dealt cards: "));
      List<ImageView> cardImageViews = getImagesFromHand(cards);
      imageCardsBox.getChildren().addAll(cardImageViews);
    });
    // Button for checking if the hand is a flush and contains "Queen of Spades".
    checkHandButton.setOnAction(e -> {
      if (hand != null && hand.isFlush()) {
        flushLabel.setText("Flush: Yes");
      } else {
        flushLabel.setText("Flush: No");
      }

      if (hand != null) {
        if (hand.checkIfHasQueenOfSpades()) {
          queensOfSpadesLabel.setText("Queen of Spades: Yes");
        } else {
          queensOfSpadesLabel.setText("Queen of Spades: No");
        }
      }
      if (hand != null) {
        List<String> heartCardNames = getCardNamesFromHand(hand.getHearts());
        heartsLabel.setText("Hearts: " + String.join(", ", heartCardNames));
      } else {
        heartsLabel.setText("No hearts");
      }
        if (hand != null) {
            sumLabel.setText("Sum of cards: " + hand.culculateSumOfCards());
        } else {
            sumLabel.setText("No cards");
        }
    });






    // Adds the buttons to the buttonBox
    userBox.getChildren().addAll(dealHandButton, checkHandButton);
    rootNode.setRight(userBox);



    BorderPane.setAlignment(dealtCardsFlow, Pos.CENTER);
    BorderPane.setMargin(dealtCardsFlow, new Insets(0, 50, 50, 50)); // Adding margin to move it down
    rootNode.setCenter(imageCardsBox);


    stage.setScene(scene);
    stage.show();
  }

  private List<String> getCardNamesFromHand(Collection<PlayingCard> hearts) {
    List<String> cardNames = new ArrayList<>();
    for (PlayingCard card : hearts) {
      cardNames.add(card.getAsString());
    }
    return cardNames;
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

