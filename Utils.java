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

public class Utils {
    public static void showInputTextDialog(String title, String header) {
        TextInputDialog dialog = new TextInputDialog(title);

        dialog.setTitle("Movie Application");
        dialog.setHeaderText(header);
        dialog.setContentText(title + ":");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            System.out.println(name);
        });
    }

    public static void manageUser(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Manage User");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text usernameText = new Text("Username :  ");
        TextField username = new TextField("Username");
        Text statusText = new Text("Status :  ");
        TextField status = new TextField("Status"); //change to dropdown
        h1.getChildren().addAll(usernameText, username, statusText, status);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Button filterButton = new Button("Filter");
        Button approveButton = new Button("Approve");
        Button declineButton = new Button("Decline");
        h2.getChildren().addAll(filterButton, approveButton, declineButton);


        // Create Table
        TableView tableView = new TableView();

        TableColumn<String, Data> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, Data> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        tableView.getItems().add(new Data("John", "Doe"));
        tableView.getItems().add(new Data("Jane", "Deer"));

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, tableView, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void manageCompany(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Manage Company");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Name");
        TextField name = new TextField("Name"); // change to dropdown
        Text coveredText = new Text("# Cities Covered");
        TextField firstAmt = new TextField();
        Text dashText1 = new Text("--");
        TextField secondAmt = new TextField();
        h1.getChildren().addAll(nameText, name, coveredText, firstAmt, dashText1, secondAmt);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text theaterText = new Text("# Theaters");
        TextField firstT = new TextField();
        Text dashText2 = new Text("--");
        TextField secondT = new TextField();
        Text employeeText = new Text("# Employees");
        TextField firstE = new TextField();
        Text dashText3 = new Text("--");
        TextField secondE = new TextField();
        h2.getChildren().addAll(theaterText, firstT, dashText2, secondT, employeeText, firstE, dashText3, secondE);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(10);
        Button filterButton = new Button("Filter");
        Button createTheaterButton = new Button("Create Theater");
        Button detailButton = new Button("Detail");
        h3.getChildren().addAll(filterButton, createTheaterButton, detailButton);


        //Create Table
        TableView tableView = new TableView();

        TableColumn<String, Data> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, Data> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        tableView.getItems().add(new Data("John", "Doe"));
        tableView.getItems().add(new Data("Jane", "Deer"));

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3, tableView, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static class Data {

        private String firstName = null;
        private String lastName = null;

        public Data() {
        }

        public Data(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}