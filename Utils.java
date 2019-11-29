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
import javafx.scene.control.CheckBox;

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
        detailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.push(Utils::manageCompany);
                companyDetail(stage, "SQL here");
            }
        });

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

    public static void createTheater(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Create Theater");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Name");
        TextField name = new TextField();
        Text companyText = new Text("Company");
        TextField company = new TextField();
        h1.getChildren().addAll(nameText, name, companyText, company);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text streetText = new Text("Street Address");
        TextField street = new TextField();
        h2.getChildren().addAll(streetText, street);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(10);
        Text cityText = new Text("City");
        TextField city = new TextField();
        Text stateText = new Text("State");
        TextField state = new TextField();
        Text zipText = new Text("Zipcode");
        TextField zip = new TextField();
        h3.getChildren().addAll(cityText, city, stateText, state, zipText, zip);

        HBox h4 = new HBox();
        h4.setAlignment(Pos.CENTER);
        h4.setSpacing(10);
        Text capacityText = new Text("Capacity");
        TextField capacity = new TextField();
        Text managerText = new Text("Manager");
        TextField manager = new TextField(); // Change to drop down
        h4.getChildren().addAll(capacityText, capacity, managerText, manager);

        HBox h5 = new HBox();
        h5.setAlignment(Pos.CENTER);
        h5.setSpacing(10);
        Button backButton = new Button("Back");
        Button createButton = new Button("Create");
        h5.getChildren().addAll(backButton, createButton);

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3, h4, h5);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void companyDetail(Stage stage, String companyName) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Company Detail");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Name: ");
        Text name = new Text(companyName);
        h1.getChildren().addAll(nameText, name);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text employeeText = new Text("Street Address");
        Text employee = new Text("add SQL here");
        h2.getChildren().addAll(employeeText, employee);

        Button backButton = new Button("Back");

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

    public static void createMovie(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Create Movie");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Name");
        TextField name = new TextField();
        Text durationText = new Text("Duration");
        TextField duration = new TextField();
        h1.getChildren().addAll(nameText, name, durationText, duration);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text releaseText = new Text("Release Date");
        TextField release = new TextField();
        h2.getChildren().addAll(releaseText, release);

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void theaterOverview(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Theater Overview");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Movie Name");
        TextField name = new TextField();
        Text durationText = new Text("Duration");
        TextField duration1 = new TextField();
        Text dashText1 = new Text("--");
        TextField duration2 = new TextField();
        h1.getChildren().addAll(nameText, name, durationText, duration1, dashText1, duration2);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text releaseText = new Text("Movie Release Date");
        TextField release1 = new TextField();
        Text dashText2 = new Text("--");
        TextField release2 = new TextField();
        Text playText = new Text("Movie Play Date");
        TextField play1 = new TextField();
        Text dashText3 = new Text("--");
        TextField play2 = new TextField();
        h2.getChildren().addAll(releaseText, release1, dashText2, release2, playText, play1, dashText3, play2);

        CheckBox checkBox = new CheckBox("Only Include Not Played Movies");

        Button filterButton = new Button("Filter");


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
        root.getChildren().addAll(title, h1, h2, checkBox, filterButton, tableView, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void scheduleMovie(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Schedule Movie");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Name");
        TextField name = new TextField(); // change to dropdown
        Text releaseText = new Text("Release Date");
        TextField release = new TextField();
        h1.getChildren().addAll(nameText, name, releaseText, release);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text playText = new Text("Play Date");
        TextField play = new TextField();
        h2.getChildren().addAll(playText, play);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(10);
        Button backButton = new Button("Back");
        Button addButton = new Button("Add");
        h3.getChildren().addAll(backButton, addButton);

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void exploreMovie(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and buttons
        Text title = new Text("Explore Movie");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        Text nameText = new Text("Movie Name");
        TextField name = new TextField(); // change to drop down
        Text companyText = new Text("Company Name");
        TextField company = new TextField();
        h1.getChildren().addAll(nameText, name, companyText, company);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(10);
        Text cityText = new Text("City");
        TextField city = new TextField();
        Text stateText = new Text("State");
        TextField state = new TextField(); // change to drop down
        h2.getChildren().addAll(cityText, city, stateText, state);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(10);
        Text playText = new Text("Movie Play Date");
        TextField play1 = new TextField();
        Text dashText3 = new Text("--");
        TextField play2 = new TextField();
        h3.getChildren().addAll(playText, play1, dashText3, play2);

        Button filterButton = new Button("Filter");


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

        HBox h4 = new HBox();
        h4.setAlignment(Pos.CENTER);
        h4.setSpacing(10);
        Button backButton = new Button("Back");
        Text cardText = new Text("Card Number");
        TextField card = new TextField(); // change to drop down
        Button viewButton = new Button("View");
        h4.getChildren().addAll(backButton, cardText, card, viewButton);

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.pastStage.pop().activate(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3, filterButton, tableView, h4);

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