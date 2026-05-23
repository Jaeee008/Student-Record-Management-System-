package com.example.studentdb.util;

import javafx.scene.control.Alert;

public final class AlertUtil {

    private AlertUtil() {}

    public static void showInfo(String message) {
        show(Alert.AlertType.INFORMATION, "Success", message);
    }

    public static void showError(String message) {
        show(Alert.AlertType.ERROR, "Error", message);
    }

    public static void showWarning(String message) {
        show(Alert.AlertType.WARNING, "Validation Error", message);
    }

    private static void show(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}