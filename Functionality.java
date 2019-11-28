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

public class Functionality {
    public static void adminFunctionalityScreen(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and
        Text title = new Text("Admin-Only Functionality");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        Button manageUserButton = new Button("Manage User");
        Button exploreTheaterButton = new Button("Explore Theater");
        h1.getChildren().addAll(manageUserButton, exploreTheaterButton);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        Button manageCompanyButton = new Button("Manage Company");
        Button visitHistoryButton = new Button("Visit History");
        h2.getChildren().addAll(manageCompanyButton, visitHistoryButton);

        Button createMovieButton = new Button("Create Movie");

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.loginScreen(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, createMovieButton, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void adminCustomerFunctionalityScreen(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and
        Text title = new Text("Admin-Customer Functionality");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        Button manageUserButton = new Button("Manage User");
        Button exploreMovieButton = new Button("Explore Movie");
        h1.getChildren().addAll(manageUserButton, exploreMovieButton);

        HBox h2 = new HBox();
        h1.setAlignment(Pos.CENTER);
        Button manageCompanyButton = new Button("Manage Company");
        Button exploreTheaterButton = new Button("Explore Theater");
        h2.getChildren().addAll(manageCompanyButton, exploreTheaterButton);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        Button createMovieButton = new Button("Create Movie");
        Button viewHistoryButton = new Button("View History");
        h3.getChildren().addAll(createMovieButton, viewHistoryButton);

        Button visitHistoryButton = new Button("Visit History");

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.loginScreen(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, h3, visitHistoryButton, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void managerFunctionalityScreen(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        //Define the layout of the text fields and
        Text title = new Text("Manager-Only Functionality");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        Button theaterOverviewButton = new Button("Theater Overview");
        Button exploreTheaterButton = new Button("Explore Theater");
        h1.getChildren().addAll(theaterOverviewButton, exploreTheaterButton);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        Button scheduleMovieButton = new Button("Schedule Movie");
        Button visitHistoryButton = new Button("Visit History");
        h2.getChildren().addAll(scheduleMovieButton, visitHistoryButton);

        Button backButton = new Button("Back");

        //Set on Click actions
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovieApplication.loginScreen(stage);
            }
        });

        //Finalize the stage
        root.getChildren().addAll(title, h1, h2, backButton);

        Scene scene = new Scene(root, 360, 480);

        stage.setScene(scene);
        stage.show();
    }
}