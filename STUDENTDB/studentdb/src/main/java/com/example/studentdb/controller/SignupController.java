package com.example.studentdb.controller;

import com.example.studentdb.MainApp;
import com.example.studentdb.service.AuthService;
import com.example.studentdb.util.AlertUtil;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private final AuthService authService = new AuthService();

    @FXML
    private void signUp() {
        try {
            authService.signUp(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            AlertUtil.showInfo("Account created successfully.");
            MainApp.changeScene("login.fxml");

        } catch (Exception e) {
            AlertUtil.showError(e.getMessage());
        }
    }

    @FXML
    private void goToLogin() {
        try {
            MainApp.changeScene("login.fxml");
        } catch (Exception e) {
            AlertUtil.showError("Failed to open login screen.");
        }
    }
}