package com.example.studentdb.controller;

import com.example.studentdb.MainApp;
import com.example.studentdb.service.AuthService;
import com.example.studentdb.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private final AuthService authService = new AuthService();

    @FXML
    private void login() {
        try {
            boolean success = authService.login(
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            if (success) {
                MainApp.changeScene("main.fxml");
            } else {
                AlertUtil.showWarning("Invalid username or password.");
            }

        } catch (Exception e) {
            AlertUtil.showError(e.getMessage());
        }
    }

    @FXML
    private void goToSignup() {
        try {
            MainApp.changeScene("signup.fxml");
        } catch (Exception e) {
            AlertUtil.showError("Failed to open sign up screen.");
        }
    }
}