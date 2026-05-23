module com.example.studentdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.studentdb to javafx.fxml;
    opens com.example.studentdb.controller to javafx.fxml;
    opens com.example.studentdb.model to javafx.base;
    requires io.github.cdimascio.dotenv.java;

    exports com.example.studentdb;
    exports com.example.studentdb.controller;
}