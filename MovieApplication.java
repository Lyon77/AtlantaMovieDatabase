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
import java.util.Stack;

public class MovieApplication extends Application {

    public static Stack<ComplexInterface> pastStage = new Stack();

    public static void main (String[] args) {
		DatabaseManager.getInstance();
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
				//System.out.println(username.getText() + " " + password.getText());	
				try {
					ResultSet login = DatabaseManager.getInstance().screen1Login(username.getText(), password.getText());
					if (login.next()) {
						if (login.getString(2).equals("Approved")) {
							System.out.println(login.getString(1) + "has logged in.");
							pastStage.push(MovieApplication::loginScreen);
							
							boolean isCustomer = login.getBoolean(3);
							boolean isAdmin = login.getBoolean(4);
							boolean isManager = login.getBoolean(5);
							
							if (isCustomer) {
								if (isAdmin) {
									Functionality.adminCustomerFunctionalityScreen(stage);
								} else if (isManager) {
									Functionality.managerCustomerFunctionalityScreen(stage);
								} else {
									Functionality.customerFunctionalityScreen(stage);
								}
							} else {
								if (isAdmin) {
									Functionality.adminFunctionalityScreen(stage);
								} else if (isManager) {
									Functionality.managerFunctionalityScreen(stage);
								} else {
									Functionality.userFunctionalityScreen(stage);
								}
							}
						} else {
							System.out.println(username.getText() + " hasn't been approved.");
						}
					} else {
						System.out.println("Invalid username or password.");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pastStage.push(MovieApplication::loginScreen);
                Registration.registerNavigation(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    @FunctionalInterface
    public static interface ComplexInterface{
        void activate(Stage stage);
    }
}