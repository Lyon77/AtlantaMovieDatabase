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
}