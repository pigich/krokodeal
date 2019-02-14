package com.krokodeal.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import static com.krokodeal.MainApp.showPersonEditDialog;


public class rootLayoutController {
    public static BooleanProperty menuItemDisable = new SimpleBooleanProperty(false);
    @FXML
    public MenuItem aboutButton;
    @FXML
    public MenuItem helpButton;
    @FXML
    public MenuItem changeMail;

    @FXML
    private void initialize() {
    }

    @FXML
    private void helpButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setHeight(500);
        alert.setContentText("This is a small help with shortcuts explanation\n\n" +
                "In New User page you can use such shortcuts:\n" +
                "Ctrl + S - show/hide password\n\n" +
                "In Login page you can use such shortcuts:\n" +
                "Ctrl + S - show/hide password\n" +
                "Ctrl + O - Chose encrypted file to read\n\n" +
                "After login or creating a new user, you will come to main page\n" +
                "where you can use such shortcuts:\n\n" +
                "Ctrl + N - Add new record\n" +
                "Ctrl + D - Delete chosen record\n" +
                "Ctrl + E - Edit chosen record\n" +
                "Ctrl + F - Go to search field to find a record\n" +
                "Ctrl + X - Logout a program\n\n");
        alert.setGraphic(null);
        alert.showAndWait();
    }

    @FXML
    private void aboutButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setHeight(300);
        alert.setGraphic(null);
//        alert.setContentText("This program was written by:\n" +
//                "Piga Pavlo  email: pigich09@gmail.com\n");
        alert.showAndWait();
    }

    public void changeMail(ActionEvent event) {
        showPersonEditDialog();
    }
}

