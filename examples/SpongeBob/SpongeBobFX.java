import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class SpongeBobFX extends Application {

    int hiPressed;
    int hiHovered;
    int hiUnhovered;
    int byePressed;
    int byeHovered;
    int byeUnhovered;
    BorderPane pane;
    ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        pane = new BorderPane();
        // pane.setAlignment(Pos.CENTER);


        pane.setCenter(getImage("pizza.gif"));
        pane.setTop(getHBox());
        pane.setLeft(getVBox());
        pane.setMinSize(650, 420);
        // pane.setRight(getGridPane());
        // pane.setBottom(getHBox);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("JavaFXApp"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private ImageView getImage(String imageName) {
        imageView = new ImageView(new Image(imageName));
        return imageView;
    }

    private HBox getHBox() {
        HBox buttonsHBox = new HBox();
        Button hiButton = new Button("Hi!");
        buttonsHBox.setSpacing(10);
        hiButton.setPrefSize(100,20);
        Button byeButton = new Button("Bye!");
        byeButton.setPrefSize(100,20);

        hiButton.setOnAction(
            event -> {
                System.out.println("pressed hi");
                hiPressed++;
                pane.setLeft(getVBox());
            }
        );
        hiButton.setOnMouseEntered(
            event -> {
                System.out.println("Hovered over OK");
                hiHovered++;
                pane.setCenter(getImage("hi.gif"));
                pane.setLeft(getVBox());
            }
        );

        hiButton.setOnMouseExited(
            event -> {
                System.out.println("Unhovered hi");
                hiUnhovered++;
                pane.setLeft(getVBox());
                pane.setCenter(getImage("pizza.gif"));
            }
        );

        byeButton.setOnAction(
            event -> {
                System.out.println("pressed bye");
                byePressed++;
                pane.setLeft(getVBox());
            }
        );
        byeButton.setOnMouseEntered(
            event -> {
                System.out.println("Hovered over bye");
                byeHovered++;
                pane.setCenter(getImage("bye.gif"));
                pane.setLeft(getVBox());
            }
        );

        byeButton.setOnMouseExited(
            event -> {
                System.out.println("Unhovered bye");
                byeUnhovered++;
                pane.setLeft(getVBox());
                pane.setCenter(getImage("pizza.gif"));
            }
        );

        buttonsHBox.getChildren().addAll(hiButton, byeButton);

        return buttonsHBox;
    }

    private VBox getVBox() {
        VBox vbox = new VBox();
        Text title = new Text("Click Count:");
        // title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Text hiPressedText = new Text("hiPressed count: " + hiPressed);
        Text heyPressedText = new Text("byePressed count: " + byePressed);
        Text hiHoveredText = new Text("hiHovered count: " + hiHovered);
        Text hiUnhoveredText = new Text("hiUnhovered count: " + hiUnhovered);
        Text heyHoveredText = new Text("byeHovered count: " + byeHovered);
        Text heyUnhoveredText = new Text("byeUnhovered count: " + byeUnhovered);

        vbox.getChildren().addAll(title, hiPressedText,
            heyPressedText, hiHoveredText, hiUnhoveredText,
            heyHoveredText, heyUnhoveredText);

        return vbox;
    }

}