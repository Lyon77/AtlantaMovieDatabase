// SQL
import java.sql.*;

// JavaFX
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import javafx.geometry.Pos;

// Java
import java.util.Optional;

public class Registration {
    public static void registerNavigation(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Define the Buttons
        Text title = new Text("Registration Navigation");

        Button userButton = new Button("User Only");
        Button customerButton = new Button("Customer Only");
        Button managerButton = new Button("Manager Only");
        Button managerCustomerButton = new Button("Manager-Customer Only");
        Button backButton = new Button("Back");

        // Set on Click Buttons
        userButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.showInputTextDialog("Name", "Input Name:");
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        // Finialize Stage
        root.getChildren().addAll(title, userButton, customerButton, managerButton, managerCustomerButton, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }
}