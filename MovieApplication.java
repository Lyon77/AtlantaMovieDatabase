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
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

// Java
import java.util.Optional;

public class MovieApplication extends Application {

    private Label label;

    public static void main (String[] args) {
        try {
            // REMEMBER to fill out the Info file and bring it outisde the template folder

            // Step 1: "Load" the JDBC driver
            // Class.forName(Info.JDBCDriver);

            // // Step 2: Establish the connection to the database
            // String url = Info.url;
            // Connection conn = DriverManager.getConnection(url, Info.username, Info.password);

        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());

            return;
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        this.label = new Label();

        Button button = new Button("Enter your name");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showInputTextDialog();
            }
        });

        root.getChildren().addAll(button, label);

        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("JavaFX TextInputDialog (o7planning.org)");
        stage.setScene(scene);

        stage.show();
    }

    private void showInputTextDialog() {

        TextInputDialog dialog = new TextInputDialog("Tran");

        dialog.setTitle("o7planning");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            this.label.setText(name);
        });
    }
}