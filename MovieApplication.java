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

public class MovieApplication extends Application {

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
        stage.setTitle("Movie Application");

        loginScreen(stage);
    }

    public static void loginScreen(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Atlanta Movie Login");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        Text usernameText = new Text("Username :  ");
        TextField username = new TextField("Username");
        h1.getChildren().addAll(usernameText, username);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        Text passwordText = new Text("Password :  ");
        TextField password = new TextField("Password");
        h2.getChildren().addAll(passwordText, password);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        h3.getChildren().addAll(loginButton, registerButton);

        //Set on Click actions
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(username.getText() + " " + password.getText());

                // Choose Functionality based on user type in SQL Database
                Functionality.userFunctionalityScreen(stage);
            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Registration.registerNavigation(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }
}