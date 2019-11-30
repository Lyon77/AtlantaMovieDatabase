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
				MovieApplication.pastStage.push(Registration::registerNavigation);
                Registration.userOnlyRegister(stage);
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
	
	public static void userOnlyRegister(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("User Registration");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER_LEFT);
        h1.setSpacing(10);
        Text firstNameText = new Text("First Name");
        TextField firstName = new TextField();
        Text lastNameText = new Text("Last Name");
        TextField lastName = new TextField();
        h1.getChildren().addAll(firstNameText, firstName, lastNameText, lastName);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER_LEFT);
        h2.setSpacing(10);
		Text usernameText = new Text("Username");
        TextField username = new TextField();
        h2.getChildren().addAll(usernameText, username);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER_LEFT);
        h3.setSpacing(10);
		Text passwordText = new Text("Password");
        TextField password = new TextField();
		Text confirmPasswordText = new Text("Confirm Password");
        TextField confirmPassword = new TextField();
        h3.getChildren().addAll(passwordText, password, confirmPasswordText, confirmPassword);

        HBox h4 = new HBox();
        h3.setAlignment(Pos.CENTER_LEFT);
        h3.setSpacing(10);
		Button backButton = new Button("Back");
		Button registerButton = new Button("Register");
        h4.getChildren().addAll(backButton, registerButton);

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });
		
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				try {
					if (password.getText().length() >= 8) {
						if (password.getText().equals(confirmPassword.getText())) {
							DatabaseManager.getInstance().screen3UserOnlyRegister(firstName.getText(), lastName.getText(),
																				username.getText(), password.getText());
							Utils.showAlert("Registration successful.");
							MovieApplication.pastStage.pop().activate(stage);
						} else {
							Utils.showAlert("Password and Confirm Password must match.");
						}
					} else {
						Utils.showAlert("Password must be at least 8 characters.");
					}
				} catch (Exception e) {
					Utils.showAlert(e.getMessage());
					System.out.println(e);
				}
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3, h4);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }
}