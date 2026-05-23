package com.example.studentdb.controller;

import com.example.studentdb.model.Student;
import com.example.studentdb.model.YearLevel;
import com.example.studentdb.repository.StudentRepository;
import com.example.studentdb.util.AlertUtil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentController {

    @FXML private TextField txtName;
    @FXML private TextField txtCourse;
    @FXML private ChoiceBox<YearLevel> cbYear;

    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, String> colYear;

    private final StudentRepository repository = new StudentRepository();
    private int selectedId = -1;

    @FXML
    public void initialize() {
        cbYear.getItems().setAll(YearLevel.values());

        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colCourse.setCellValueFactory(data -> data.getValue().courseProperty());
        colYear.setCellValueFactory(data -> data.getValue().yearLevelProperty());

        table.setOnMouseClicked(event -> populateFields());

        loadStudents();
    }

    @FXML
    private void addStudent() {
        if (!isValidInput()) return;

        try {
            repository.save(
                    txtName.getText().trim(),
                    txtCourse.getText().trim(),
                    cbYear.getValue().toString()
            );

            loadStudents();
            clearFields();
            AlertUtil.showInfo("Student added successfully.");

        } catch (Exception e) {
            AlertUtil.showError("Failed to add student.");
        }
    }

    @FXML
    private void updateStudent() {
        if (selectedId == -1) {
            AlertUtil.showWarning("Please select a student to update.");
            return;
        }

        if (!isValidInput()) return;

        try {
            repository.update(
                    selectedId,
                    txtName.getText().trim(),
                    txtCourse.getText().trim(),
                    cbYear.getValue().toString()
            );

            loadStudents();
            clearFields();
            AlertUtil.showInfo("Student updated successfully.");

        } catch (Exception e) {
            AlertUtil.showError("Failed to update student.");
        }
    }

    @FXML
    private void deleteStudent() {
        if (selectedId == -1) {
            AlertUtil.showWarning("Please select a student to delete.");
            return;
        }

        try {
            repository.delete(selectedId);
            loadStudents();
            clearFields();
            AlertUtil.showInfo("Student deleted successfully.");

        } catch (Exception e) {
            AlertUtil.showError("Failed to delete student.");
        }
    }

    @FXML
    private void clearFields() {
        txtName.clear();
        txtCourse.clear();
        cbYear.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedId = -1;
    }

    private void loadStudents() {
        try {
            table.setItems(FXCollections.observableArrayList(repository.findAll()));
        } catch (Exception e) {
            AlertUtil.showError("Failed to load student records.");
        }
    }

    private void populateFields() {
        Student student = table.getSelectionModel().getSelectedItem();

        if (student == null) return;

        selectedId = student.getId();
        txtName.setText(student.getName());
        txtCourse.setText(student.getCourse());

        for (YearLevel year : YearLevel.values()) {
            if (year.toString().equals(student.getYearLevel())) {
                cbYear.setValue(year);
                break;
            }
        }
    }

    private boolean isValidInput() {
        if (txtName.getText().trim().isEmpty()) {
            AlertUtil.showWarning("Name is required.");
            return false;
        }

        if (txtCourse.getText().trim().isEmpty()) {
            AlertUtil.showWarning("Course is required.");
            return false;
        }

        if (cbYear.getValue() == null) {
            AlertUtil.showWarning("Year level is required.");
            return false;
        }

        return true;
    }
}