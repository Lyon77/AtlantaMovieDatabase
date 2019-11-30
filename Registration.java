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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
		
		customerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				MovieApplication.pastStage.push(Registration::registerNavigation);
                Registration.customerOnlyRegister(stage);
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
        h4.setAlignment(Pos.CENTER_LEFT);
        h4.setSpacing(10);
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
	
	public static void customerOnlyRegister(Stage stage) {
		int creditCardCount = 0;
		
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
        h4.setAlignment(Pos.CENTER_LEFT);
        h4.setSpacing(10);
		Button addCardButton = new Button("Add Card");
		TextField cardEntryField = new TextField();
		Button removeCardButton = new Button("Remove Card");
        h4.getChildren().addAll(addCardButton, cardEntryField, removeCardButton);

        HBox h5 = new HBox();
        h5.setAlignment(Pos.CENTER_LEFT);
        h5.setSpacing(10);
		Button backButton = new Button("Back");
		Button registerButton = new Button("Register");
        h5.getChildren().addAll(backButton, registerButton);
		
		
		//Credit cards
		TableView tableView = new TableView();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<String, Utils.CreditCardData> column1 = new TableColumn<>("Credit Card Numbers");
        column1.setCellValueFactory(new PropertyValueFactory<>("creditCardNum"));
		
		tableView.getColumns().add(column1);

        

        //Set on Click actions
		addCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				if (cardEntryField.getText().length() == 16) {
					if (cardEntryField.getText().matches("[0-9]+")) {
						Utils.CreditCardData creditCard = new Utils.CreditCardData(cardEntryField.getText());
						if (!tableView.getItems().contains(creditCard)) {
							tableView.getItems().add(creditCard);
							if (tableView.getItems().size() == 5) {
								addCardButton.setDisable(true);
							}
						} else {
							Utils.showAlert("Duplicate card detected.");
						}
					} else {
						Utils.showAlert("Credit cards can only contain digits.");
					}
				} else {
					Utils.showAlert("Credit cards must be 16 digits.");
				}
            }
        });
		
		removeCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				Utils.CreditCardData selectedCard = (Utils.CreditCardData)tableView.getSelectionModel().getSelectedItem();
				tableView.getItems().remove(selectedCard);
				addCardButton.setDisable(false);
            }
        });
		
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
							if (tableView.getItems().size() >= 1) {
								DatabaseManager.getInstance().screen4CustomerOnlyRegister(firstName.getText(), lastName.getText(),
																					username.getText(), password.getText());
								for (Object card : tableView.getItems()) {
									DatabaseManager.getInstance().customerAddCreditCard(username.getText(), ((Utils.CreditCardData)card).getCreditCardNum());
								}
								Utils.showAlert("Registration successful.");
								MovieApplication.pastStage.pop().activate(stage);
							} else {
								Utils.showAlert("You must enter at least 1 credit card.");
							}
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
        root.getChildren().addAll(title, h1, h2, h3, tableView, h4, h5);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }
}